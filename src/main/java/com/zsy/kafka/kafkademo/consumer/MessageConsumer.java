//package com.zsy.kafka.kafkademo.consumer;
//
//import com.alibaba.fastjson.JSON;
//import com.zsy.kafka.kafkademo.message.Message;
//import com.zsy.kafka.kafkademo.topic.TopicConst;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageConsumer {
//
//    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
//
//    @KafkaListener(topics = TopicConst.PAY_TOPIC)
//    public void onMessage(String message) {
//        Message msg = JSON.parseObject(message, Message.class);
//        logger.info("messageConsumer is: " + message );
//        System.out.println("code-->"+msg.getOrderCode());
//    }
//}
