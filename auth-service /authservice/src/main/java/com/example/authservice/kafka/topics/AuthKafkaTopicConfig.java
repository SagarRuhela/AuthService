package com.example.authservice.kafka.topics;


import com.example.authservice.constant.KafkaTopicsNames;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AuthKafkaTopicConfig {

    @Bean
    public NewTopic userRegisteredTopic() {
        return TopicBuilder.name(KafkaTopicsNames.USER_REGISTERED)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
