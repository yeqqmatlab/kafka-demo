package com.zsy.kafka.kafkademo.kafkaAdmin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * created by yqq 2020/3/25
 */
@RestController
@RequestMapping("/kafka-topic")
public class TestCreateTopic {

    @Autowired
    private AdminClient adminClient;

    @RequestMapping("add/{topic}")
    public String createTopic(@PathVariable("topic") String topic) throws ExecutionException, InterruptedException {

        ListTopicsResult listTopics = adminClient.listTopics();
        Set<String> topicNameSet= listTopics.names().get();

        if (!topicNameSet.contains(topic)) {
            NewTopic newTopic = new NewTopic(topic,3,(short) 3);
            adminClient.createTopics(Arrays.asList(newTopic));
            //Thread.sleep(1000*5);
        }

        return "success";
    }




}
