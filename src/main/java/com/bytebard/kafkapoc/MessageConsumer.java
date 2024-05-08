package com.bytebard.kafkapoc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "topic", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
