package com.sshmygin.mailsenderms.kafka.consumer;

import com.sshmygin.customerms.model.Customer;

public interface KafkaCustomerConsumer {
    void consume(Customer customer);
}
