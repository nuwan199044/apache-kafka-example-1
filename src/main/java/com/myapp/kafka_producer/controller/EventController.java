package com.myapp.kafka_producer.controller;

import com.myapp.kafka_producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    KafkaMessagePublisher publisher;

    @GetMapping("/produce/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            publisher.sendMessageToTopic("facebook",1);
            publisher.sendMessageToTopic("youtube",2);
            publisher.sendMessageToTopic("linkdn",2);
            publisher.sendMessageToTopic("tiktok",3);
            publisher.sendMessageToTopic("twitter",4);
            publisher.sendMessageToTopic("discode",0);
            return ResponseEntity.ok("message published successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
