package com.sshmygin.mailsenderms.service;

import com.sshmygin.customerms.model.Customer;
import com.sshmygin.mailsenderms.exception.EmailRecordNotFoundException;
import com.sshmygin.mailsenderms.exception.MailSenderException;
import com.sshmygin.mailsenderms.model.EmailRecord;
import com.sshmygin.mailsenderms.repository.EmailRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailRecordService {
    private final EmailRecordRepository emailRecordRepository;

    public void addEmailRecord(Customer customer) {
        EmailRecord existingEmailRecord = emailRecordRepository.findByEmail(customer.getEmail()).orElse(null);
        if (existingEmailRecord == null) {
            EmailRecord newEmailRecord = new EmailRecord(customer.getEmail(), customer.getActivationCode());
            emailRecordRepository.save(newEmailRecord);
        } else {
            log.error("MailSenderMS Adding new record about email -> Couldn't add a record about email -> Record about email {} already exists", customer.getEmail());
            throw new EmailRecordNotFoundException("Record about email {} already exists" + customer.getEmail());
        }

    }
}
