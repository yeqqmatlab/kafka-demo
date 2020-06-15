package com.zsy.kafka.kafkademo.producer;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.MsgObj;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka")
public class TestKafkaProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("send/{topic}/{msg}")
    public String send(@PathVariable("topic") String topic, @PathVariable("msg") String msg) throws ExecutionException, InterruptedException {

        //kafkaTemplate.send(topic,0,"000", msg);
        //kafkaTemplate.send(topic,1,"001", msg);
        kafkaTemplate.send(topic, msg);
        return "success";
    }



}
