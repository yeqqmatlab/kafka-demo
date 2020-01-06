package com.zsy.kafka.kafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

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

    /**
     * 手动提交offset ,出现异常,offset不提交,消息不会丢失。
     */
    @KafkaListener(id="009", topics = "topic_01")
    public void consumerListener(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
