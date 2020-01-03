package com.zsy.kafka.kafkademo;

import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.producer.MessageProducer;
import com.zsy.kafka.kafkademo.utils.ToolsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * producer
     */
    @Test
    public void producer(){

        MessageProducer producer = new MessageProducer();

        while (true){
            Message message = new Message();
            message.setFee(ToolsUtil.getRandFloat());
            message.setOrderCode(ToolsUtil.getNextCode());
            message.setSendTime(System.currentTimeMillis());
            producer.send(message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
