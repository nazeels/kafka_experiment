package com.wariyum.api.nearme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/")
    public String test(){
        ListenableFuture<SendResult<String, String>> send = this.kafkaTemplate.send("users", "test message");
        return send.toString();
    }

    @KafkaListener(topics = "users",groupId  = "foo")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }
}
