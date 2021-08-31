package com.sshmygin.mailsenderms.kafka.consumer;

import com.sshmygin.customerms.model.Customer;
import com.sshmygin.mailsenderms.service.EmailRecordService;
import com.sshmygin.mailsenderms.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaCustomerConsumerService implements KafkaCustomerConsumer {

    private final MailSenderService mailSenderService;
    private final EmailRecordService emailRecordService;

    @Override
    @KafkaListener(topics = "send")
    public void consume(Customer customer) {
        log.info("MailSenderMS KafkaEmailProducerService -> Consumed message -> {}", customer);

        emailRecordService.addEmailRecord(customer);
        mailSenderService.sendConfirmationEmail(customer);
    }
}
