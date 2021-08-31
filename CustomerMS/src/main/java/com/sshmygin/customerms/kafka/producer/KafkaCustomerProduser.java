package com.sshmygin.customerms.kafka.producer;

import com.sshmygin.customerms.model.Customer;

public interface KafkaCustomerProduser {
    void send(Customer customer);
}
