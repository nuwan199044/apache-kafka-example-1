package com.myapp.kafka_producer.controller;

import com.myapp.dto.Employee;
import com.myapp.kafka_producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class EventController {

    @Autowired
    KafkaMessagePublisher publisher;

    @PostMapping("/event")
    public ResponseEntity<?> publishMessage(@RequestBody Employee employee) {
        try {
            publisher.sendEventToTopic(employee);
            return ResponseEntity.ok("message published successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
