package com.zsy.kafka.kafkademo.producer;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.utils.topic.TopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class MessageProducer {

    private static Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(Message payMessage) throws ExecutionException, InterruptedException {
        String msg = JSON.toJSONString(payMessage);
        /**
         * 异步发送消息
         */
        kafkaTemplate.send(TopicConst.TOPIC_A, msg);
        logger.info("messageProducer is: " + msg );

        /**
         * 同步发送消息
         */
        //Object o = kafkaTemplate.send(TopicConst.PAY_TOPIC, msg).get();

        /*ListenableFuture send = kafkaTemplate.send(TopicConst.PAY_TOPIC, msg);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                System.out.println("1 messageProducer is: "+result.getProducerRecord());
                System.out.println("2 messageProducer is: "+result.getRecordMetadata());
            }
        });*/
    }

    /**
     *
     * @param topic
     * @param msg
     * @throws ExecutionException
     */
    public void send(String topic,Object msg) throws ExecutionException{

        kafkaTemplate.send(topic, msg);
        logger.info("messageProducer is: " + msg.toString() );
    }

    /**
     *
     * @param topic
     * @param partition
     * @param msg
     * @throws ExecutionException
     */
    public void send(String topic,Integer partition,String msg) throws ExecutionException{

        kafkaTemplate.send(topic, partition,null, msg);
        logger.info("messageProducer is: " + msg );
    }

}
