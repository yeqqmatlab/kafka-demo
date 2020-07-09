package com.zsy.kafka.kafkademo;

import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.producer.MessageProducer;
import com.zsy.kafka.kafkademo.utils.ToolsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);

        MessageProducer producer = applicationContext.getBean(MessageProducer.class);

        for (int i = 0; i < 500; i++) {
            Message message = new Message();
            message.setFee(ToolsUtil.getRandFloat());
            message.setOrderCode(ToolsUtil.getNextCode());
            message.setSendTime(System.currentTimeMillis());
            producer.send(message);
        }
        for (int i = 0; i < 60; i++) {
            Message message = new Message();
            message.setFee(0);
            message.setOrderCode(ToolsUtil.getNextCode());
            message.setSendTime(System.currentTimeMillis());
            producer.send(message);
        }
        for (int i = 0; i < 500; i++) {
            Message message = new Message();
            message.setFee(ToolsUtil.getRandFloat());
            message.setOrderCode(ToolsUtil.getNextCode());
            message.setSendTime(System.currentTimeMillis());
            producer.send(message);
        }

        /*while (true){
            Message message = new Message();
            message.setFee(ToolsUtil.getRandFloat());
            message.setOrderCode(ToolsUtil.getNextCode());
            message.setSendTime(System.currentTimeMillis());
            producer.send(message);

        }*/
    }

}
