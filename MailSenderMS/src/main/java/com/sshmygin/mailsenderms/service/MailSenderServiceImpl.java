package com.sshmygin.mailsenderms.service;

import com.sshmygin.customerms.model.Customer;
import com.sshmygin.mailsenderms.exception.MailSenderException;
import com.sshmygin.mailsenderms.kafka.producer.KafkaEmailProduser;
import com.sshmygin.mailsenderms.model.EmailRecord;
import com.sshmygin.mailsenderms.model.MailContent;
import com.sshmygin.mailsenderms.repository.EmailRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;
    private final EmailRecordRepository emailRecordRepository;
    private final KafkaEmailProduser kafkaEmailProduser;

    @Override
    public void sendConfirmationEmail(Customer customer) {

        EmailRecord emailRecord = emailRecordRepository.findByEmail(customer.getEmail()).orElse(null);

        if (emailRecord == null) {
            log.error("MailSenderMS Sending confirmation email -> Data of an email {} wasn't found in database. Customer hasn't been registered.", customer.getEmail());
            throw new MailSenderException("Customer with email " + customer.getEmail() + " hasn't been registered.");

        } else if (emailRecord.getDateOfConfirmation() != null) {
            log.error("MailSenderMS Sending confirmation email -> Email was already sent on address: {}", emailRecord.getEmail());
            throw new MailSenderException("Email was already sent on address: " + emailRecord.getEmail());
        } else {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Email confirmation");
            mailMessage.setText(String.format("%s\n%s", MailContent.CONFIRMATION_EMAIL.content, customer.getActivationCode()));
            mailSender.send(mailMessage);
            log.info("MailSenderMS Sending confirmation email -> Confirmation email has been sent -> Email address: {}", customer.getEmail());

            emailRecord.setDateOfSending(LocalDateTime.now());
            emailRecordRepository.save(emailRecord);
        }
    }


    public void sendEmailActivationRequest(String code) {
        EmailRecord emailRecord = emailRecordRepository.findByActivationCode(code).orElse(null);
        if (emailRecord == null) {
            log.error("Couldn't activate account. Wrong activation code");
            throw new MailSenderException("Wrong activation code.");
        } else if (emailRecord.getDateOfConfirmation() != null) {
            log.error("Email was already activated! Email: " + emailRecord.getEmail());
            throw new MailSenderException("Email was already activated! Email: " + emailRecord.getEmail());
        } else {
            emailRecord.setDateOfConfirmation(LocalDateTime.now());
            emailRecordRepository.save(emailRecord);
            kafkaEmailProduser.send(emailRecord.getEmail());
        }
    }

}
