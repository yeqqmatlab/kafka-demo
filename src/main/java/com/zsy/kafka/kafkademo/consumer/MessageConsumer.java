package com.zsy.kafka.kafkademo.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;

@Component
public class MessageConsumer {


    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);



   /* @KafkaListener(id="001", groupId="test-group-01",topics = "topic_05")
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

    /*@KafkaListener(topics = TopicConst.PAY_TOPIC)
    public void onMessage(String message) throws InterruptedException {
        Thread.sleep(1000*1);
        MsgObj msg = JSON.parseObject(message, MsgObj.class);
        //logger.info("messageConsumer is: " + message );
        System.out.println("code-->"+msg.getCode());
        System.out.println("code-->"+msg.getMsg());
    }*/

    /**
     * 手动提交offset ,出现异常,offset不提交,消息不会丢失。
     */
    /*@KafkaListener(id="009",groupId="test-group-06", topics = "test_group_id")
    public void consumerListener3(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("test-group-01:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            //Thread.sleep(1000);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*@KafkaListener(id="100",groupId="test-group-01", topics = TopicConst.PAY_TOPIC20,concurrency = "20",properties= {"max.poll.interval.ms = 600000","session.timeout.ms = 15000","heartbeat.interval.ms = 5000"})
    public void consumerListener100(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("consumerListener100:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            Thread.sleep(1000*1);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /**
     * 并发消费
     * max.poll.interval.ms 向 kafka broker poll offset 的时间间隔最大不能超过10分钟
     * @param record
     * @param ack
     * @throws InterruptedException
     */
    /*@KafkaListener(id="103",groupId="test-group-02", topics = TopicConst.PAY_TOPICB20,concurrency = "20",properties= {"max.poll.interval.ms = 600000","session.timeout.ms = 15000","heartbeat.interval.ms = 5000"})
    public void consumerListener103(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("consumerListener103:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());

            *//**
             * 多线程累计计数
             *//*
            synchronized (TestConsumer.class){
                ++count;
                System.out.println(Thread.currentThread().getName()+":count-->"+count);
            }
            Thread.sleep(1000*1);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*@KafkaListener(id="101",groupId="test-group-01", topics = TopicConst.PAY_TOPIC20)
    public void consumerListener101(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("consumerListener101:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            //Thread.sleep(1000*1);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*@KafkaListener(id="010",groupId="test-group-01", topics = TopicConst.PAY_TOPIC2)
    public void consumerListener2(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("test-group-01:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*@KafkaListener(id="08",groupId="test-group-01", topics = TopicConst.PAY_TOPIC)
    public void consumerListener(ConsumerRecord<?, ?> record, Acknowledgment ack) throws InterruptedException {
        try {
            System.out.printf("test-group-01:"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /***
     * 手动为消费 指定消费对应的分区 assign方式
     * 默认是自动subscribe分配分区,同时进行“自动”和“手动”的分区分配是会互相影响的
     * @param record
     * @param ack
     */
    /*@KafkaListener(id="010", groupId="test-group-01", topics = TopicConst.PAY_TOPIC20,concurrency = "20",errorHandler="consumerAwareErrorHandler")
    public void consumerListener010(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //Thread.sleep(100);
            System.out.printf("010test-group-02:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            String message = (String) record.value();
            Message msg = JSON.parseObject(message, Message.class);
            Integer fee = msg.getFee();
            int k = 1 / fee;
            System.out.println("fee-->"+fee);
            ack.acknowledge();
        } catch (Exception e) {
            System.out.printf("Exception--010test-group-02:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            //kafkaTemplate.send(TopicConst.PAY_TOPIC_FAILURE,record.value());
            e.printStackTrace();
        }
    }*/

    /*@Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {

        return new ConsumerAwareListenerErrorHandler() {
            @Override
            public Object handleError(org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                logger.info("consumerAwareErrorHandler receive : "+message.getPayload().toString());
                return null;
            }
        };
    }*/



    /*@KafkaListener(id="013", topicPartitions = {@TopicPartition(topic = "topic_03",partitions = {"0","2","3","4"})},groupId = "test-group-02")
    public void consumerListener013(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            //int k = 1/0;
            System.out.printf("013test-group-01:topic_01--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

//    @KafkaListener(id="011",groupId="test-group-02", topics = "kafkaTest")
//    public void consumerListener011(ConsumerRecord<?, ?> record, Acknowledgment ack){
//
//        try {
//            System.out.printf("test-group-01:topic_06--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
//
//            RtdbRecord vo = new RtdbRecord();
//            vo.setTableName("fes_group");
//            vo.setTableFieldKeys(Arrays.asList(
//                    "group_label", "group_tag", "group_id", "domain_id",
//                    "station_id", "pro_system_id", "group_desc", "run_mode",
//                    "redundancy_port", "cli_tool_port", "para3", "para4",
//                    "crc32_code"
//            ));
//            vo.setTableFieldVals(Arrays.asList(
//                    new RtdbVariant("test01"), new RtdbVariant("test01"), new RtdbVariant(876), new RtdbVariant(1),
//                    new RtdbVariant(1), new RtdbVariant(1), new RtdbVariant("test01"), new RtdbVariant(2),
//                    new RtdbVariant(8888), new RtdbVariant(7777), new RtdbVariant("par333"), new RtdbVariant("par444"),
//                    new RtdbVariant(3467827869L)
//            ));
//            RtdbClient.insertTableRecord(1, vo);
//
//            //手动提交
//            ack.acknowledge();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

/*    @KafkaListener(id = "test_001",groupId="test-group-02", topics = "topic_07", containerFactory = "ackContainerFactory")
    public void consumerListener012(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            System.out.printf("test-group-02:topic_07--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            String value = (String)record.value();
            System.out.println("value--->"+value);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*@KafkaListener(id = "test01", groupId="pay-group-01", topicPattern = "pay-topic.*")
    public void consumerListener016(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            System.out.printf("test01-topic--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            String value = (String)record.value();
            System.out.println("value--->"+value);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*@KafkaListener(id = "test02", groupId="pay-group-01", topicPattern = "pay-topic.*")
    public void consumerListener017(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            System.out.printf("test02-topic--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            String value = (String)record.value();
            System.out.println("value--->"+value);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(id = "test03", groupId="pay-group-01", topicPattern = "pay-topic.*")
    public void consumerListener018(ConsumerRecord<?, ?> record, Acknowledgment ack){

        try {
            System.out.printf("test03-topic--->"+"topic = %s,partition = %s, offset = %d,key = %s, value = %s \n", record.topic(),record.partition(),record.offset(),record.key(),record.value());
            String value = (String)record.value();
            System.out.println("value--->"+value);
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



}
