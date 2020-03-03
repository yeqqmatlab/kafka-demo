package com.zsy.kafka.kafkademo.consumer;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.message.MsgObj;
import com.zsy.kafka.kafkademo.topic.TopicConst;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TestConsumer {

    private static Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    /*@KafkaListener(id="001", groupId="test-group-01",topics = "topic_01")
    public void listenTestGroup01Topic01 (ConsumerRecord<?, ?> record) throws Exception {
        //int i = 1 / 0;
        System.out.printf("test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
    }*/

/*    @KafkaListener(id="002",groupId="test-group-01",topics = "topic_03")
    public void listenTestGroup01Topic01B (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("test-group-01:topic_03:B--->"+"topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(id="003",groupId="test-group-01",topics = "topic_03")
    public void listenTestGroup01Topic01C (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("test-group-01:topic_03:C--->"+"topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }*/

/*    @KafkaListener(groupId="test-group-02",topics = "topic_01")
    public void listenTestGroup02Topic01 (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("test-group-02:topic_01--->"+"topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

    @KafkaListener(groupId="test-group-01",topics = "topic_02")
    public void listenTestGroup01Topic02 (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("test-group-01:topic_02--->"+"topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }*/

    /*@KafkaListener(topics = TopicConst.TOPIC_01)
    public void onMessage(String message) {
        MsgObj msg = JSON.parseObject(message, MsgObj.class);
        logger.info("messageConsumer is: " + message );
        System.out.println("code-->"+msg.getCode());
        System.out.println("code-->"+msg.getMsg());
    }*/

    /**
     * 手动提交offset ,出现异常,offset不提交,消息不会丢失。
     */
    /*@KafkaListener(id="009",groupId="test-group-01", topics = "topic_03")
    public void consumerListener(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("009test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

   /* @KafkaListener(topicPartitions = {@TopicPartition(topic = "topic_03",partitions = {"1"})})
    public void consumerListener010(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("010test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    @KafkaListener(id="011",groupId="test-group-01", topics = "topic_03")
    public void consumerListener011(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("011test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*@KafkaListener(id="012",groupId="test-group-02", topics = "topic_03")
    public void consumerListener012(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("012test-group-02:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/






}
