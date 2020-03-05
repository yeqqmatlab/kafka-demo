package com.zsy.kafka.kafkademo.producer;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.MsgObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TestKafkaProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("send/{topic}/{msg}")
    public String send(@PathVariable("topic") String topic, @PathVariable("msg") String msg){

        /*MsgObj msg1 = new MsgObj();
        msg1.setCode("001");
        msg1.setMsg(msg);
        String msgStr = JSON.toJSONString(msg1);
        System.out.println("msgStr-->"+msgStr);*/
        //kafkaTemplate.send(topic, msg);
        //key相同，则数据在同一个partition中
        //kafkaTemplate.send(topic,0,"000", msg);
        //kafkaTemplate.send(topic,1,"001", msg);
        //普通发送消息
        kafkaTemplate.send(topic, msg);

        //System.out.println("send-->"+send);
        return "success";
    }

}
