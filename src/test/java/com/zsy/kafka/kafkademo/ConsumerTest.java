package com.zsy.kafka.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {

    @Test
    public void TestCon() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.239:9092,192.168.1.247:9092,192.168.1.248:9092");
        props.put("group.id", "test-group-01");
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 5);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("topic_01"));
        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(3000);
            System.out.println("polls out: " + ++i + ", time: " + System.currentTimeMillis());

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("time = %s, partition = %s, offset = %d, key = %s, value = %s%n",
                        record.timestamp(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value());
            }
            consumer.commitSync();
        }
    }

}
