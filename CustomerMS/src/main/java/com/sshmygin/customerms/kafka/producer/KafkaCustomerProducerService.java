package com.sshmygin.customerms.kafka.producer;

import com.sshmygin.customerms.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaCustomerProducerService implements KafkaCustomerProduser {

    private static final String TOPIC = "send";
    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public void send(Customer customer) {
        log.info("CustomerMS KafkaCustomerProducer -> Producing message of a customer -> Customer: {}", customer);
        kafkaTemplate.send(TOPIC, customer);
    }


}
