package com.zsy.kafka.kafkademo;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.producer.MessageProducer;
import com.zsy.kafka.kafkademo.utils.topic.TopicConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@EnableFeignClients
@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);

        MessageProducer producer = applicationContext.getBean(MessageProducer.class);

        for (int j = 0; j < 500; j++) {
            Thread.sleep(1000*1);
            Float[] arr = new Float[]{0f,0f,0f,5f,2f,2f,2f,2f,2f,2f,2f,2f,2f,2f,0.5f,0.5f,0.5f,0.5f,0f,0f};
            for (int i = 2; i < 18; i++) {
                Random random = new Random();
                float randomFloat = random.nextFloat() * 0.2f;
                System.out.println("randomFloat = " + randomFloat);
                arr[i] = arr[i] + randomFloat;
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedNumber = decimalFormat.format(arr[i]);
                arr[i]  = Float.valueOf(formattedNumber);
            }
            String msg = JSON.toJSONString(arr);
            producer.send(TopicConst.TOPIC_A, msg);
        }

//        while (true){
//            Thread.sleep(1000*1);
//            int[] arr = new int[6];
//            arr[0] = ToolsUtil.getRandInt();
//            arr[1] = ToolsUtil.getRandInt();
//            arr[2] = ToolsUtil.getRandInt();
//            arr[3] = ToolsUtil.getRandInt();
//            arr[4] = ToolsUtil.getRandInt();
//            arr[5] = ToolsUtil.getRandInt();
//            String msg = JSON.toJSONString(arr);
//            producer.send(TopicConst.TOPIC_A, msg);
//        }

    }

}
