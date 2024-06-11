package com.myapp.kafka_producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
    @Autowired
    KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> customerTopic = template.send("customerTopic", message);
        customerTopic.whenComplete((result, exception)->{
            if (exception == null) {
                System.out.println("Sent message=[ "+message+ "] with offset=[ "+result.getRecordMetadata().offset()+" ]");
            } else {
                System.out.println("Unable to send message=[ "+message+ " ] due to: "+exception.getMessage());
            }
        });
    }
}
