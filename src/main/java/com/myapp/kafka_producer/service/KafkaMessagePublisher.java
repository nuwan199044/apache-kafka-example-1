package com.myapp.kafka_producer.service;

import com.myapp.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {
    @Autowired
    KafkaTemplate<String, Employee> template;

    public void sendEventToTopic(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> customerTopic = template.send("employeeTopic-avro", UUID.randomUUID().toString(), employee);
        customerTopic.whenComplete((result, exception)->{
            if (exception == null) {
                System.out.println("Sent message=[ "+employee.toString()+ "] with offset=[ "+result.getRecordMetadata().offset()+" ]");
            } else {
                System.out.println("Unable to send message=[ "+employee+ " ] due to: "+exception.getMessage());
            }
        });
    }
}
