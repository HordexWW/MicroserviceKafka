package com.sshmygin.mailsenderms.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEmailProducerService implements KafkaEmailProduser {

    private static final String TOPIC = "activate";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String email) {
        log.info("MailSenderMS KafkaEmailProducerService -> Producing email -> {}", email);
        kafkaTemplate.send(TOPIC, email);
    }
}
