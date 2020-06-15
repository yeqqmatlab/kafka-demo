package com.zsy.kafka.kafkademo.producer;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.topic.TopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(Message payMessage) {
        String msg = JSON.toJSONString(payMessage);
        kafkaTemplate.send(TopicConst.PAY_TOPIC3, msg);
        //logger.info("messageProducer is: " + msg );

    }
}
