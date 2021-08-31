package com.sshmygin.customerms.kafka.consumer;

public interface KafkaEmailConsumer {
    void consume(String email);
}
