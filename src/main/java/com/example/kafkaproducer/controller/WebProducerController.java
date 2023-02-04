package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class WebProducerController {

    @Value("${topic-name}")
    private String topicName;
    private final KafkaTemplate<String, UserModel> kafkaTemplate;

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable String message) {

        UserModel userModel = UserModel.builder()
                .id(-1L)
                .name(message)
                .build();

        kafkaTemplate.send(topicName, userModel);
        return topicName + " - sending user with name: " + message;
    }

}
