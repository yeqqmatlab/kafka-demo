package com.zsy.kafka.kafkademo;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.InnerObj;
import com.zsy.kafka.kafkademo.message.MessageObj;
import com.zsy.kafka.kafkademo.producer.MessageProducer;
import com.zsy.kafka.kafkademo.utils.topic.TopicConst;
import com.zsy.kafka.kafkademo.utils.ToolsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);

        MessageProducer producer = applicationContext.getBean(MessageProducer.class);

//        for (int i = 0; i < 100; i++) {
//            Thread.sleep(500);
//            producer.send(TopicConst.TOPIC_A, "java"+i);
//        }

    }

}
