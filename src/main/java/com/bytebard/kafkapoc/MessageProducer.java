package com.bytebard.kafkapoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Unable to send message: " + ex.getMessage());
                return;
            }
            System.out.println("Message sent: " + message + " with offset: " + result.getRecordMetadata().offset());
            System.out.println("Record metadata: " + result.getRecordMetadata());
        });
    }
}
