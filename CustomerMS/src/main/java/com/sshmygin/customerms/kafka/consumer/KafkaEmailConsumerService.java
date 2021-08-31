package com.sshmygin.customerms.kafka.consumer;

import com.sshmygin.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEmailConsumerService implements KafkaEmailConsumer {

    private final CustomerService customerService;

    @Override
    @KafkaListener(topics = "activate")
    public void consume(String email) {
        log.info("CustomerMS KafkaCustomerConsumer -> Consuming message of a customer's email -> Email: {}", email);
        customerService.activateCustomerAccount(email);
    }
}
