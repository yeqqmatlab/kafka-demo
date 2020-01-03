package com.zsy.kafka.kafkademo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TestKafkaProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("send/{topic}/{msg}")
    public String send(@PathVariable("topic") String topic, @PathVariable("msg") String msg){
        kafkaTemplate.send(topic, msg);
        return "success";
    }

}
