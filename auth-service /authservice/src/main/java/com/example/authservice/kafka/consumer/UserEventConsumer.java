package com.example.authservice.kafka.consumer;

import com.example.authservice.constant.KafkaTopicsNames;
import com.example.authservice.dto.events.UserRegisteredEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    @KafkaListener(
            topics = KafkaTopicsNames.USER_REGISTERED,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleUserRegistered(UserRegisteredEvent event) {
        System.out.println("Consumed USER_REGISTERED: " + event);
    }
}
