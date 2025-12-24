package com.example.authservice.kafka.producer;

import com.example.authservice.constant.KafkaTopicsNames;
import com.example.authservice.dto.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        kafkaTemplate.send(KafkaTopicsNames.USER_REGISTERED, event);
    }
}

