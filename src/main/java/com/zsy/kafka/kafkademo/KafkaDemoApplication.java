package com.zsy.kafka.kafkademo;

import com.alibaba.fastjson.JSON;
import com.zsy.kafka.kafkademo.message.InnerObj;
import com.zsy.kafka.kafkademo.message.Message;
import com.zsy.kafka.kafkademo.message.MessageA;
import com.zsy.kafka.kafkademo.message.MessageObj;
import com.zsy.kafka.kafkademo.producer.MessageProducer;
import com.zsy.kafka.kafkademo.topic.TopicConst;
import com.zsy.kafka.kafkademo.utils.ToolsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);

        MessageProducer producer = applicationContext.getBean(MessageProducer.class);


        String[] labelArr = {"dl1:sl1:BAS:dl4216:iInTemp", "dl1:sl1:BAS:dl10166:iAscCurr",
                "dl1:sl1:BAS:dl10166:iAsrVolt",
                "dl1:sl1:BAS:dl10166:iAscVolt",
                "dl1:sl1:BAS:dl10166:iBscVolt",
                "dl1:sl1:BAS:dl10166:iBsrVolt",
                "dl1:sl1:BAS:dl10166:iBscCurr",
                "dl1:sl1:BAS:dl10166:iCscVolt",
                "dl1:sl1:BAS:dl10166:iCscCurr",
                "dl1:sl1:BAS:dl10166:icdCurr",
                "dl1:sl1:BAS:dl10166:iCsrVolt",
                "dl1:sl1:BAS:dl10166:ifdCurr",
                "dl1:sl1:BAS:dl10166:izVolt",
                "dl1:sl1:BAS:dl2212:sModeATS",
                "dl1:sl1:BAS:dl2212:sModePC",
                "dl1:sl1:BAS:dl2212:sModeSmart",
                "dl1:sl1:BAS:dl2212:sModeISCS",
                "dl1:sl1:BAS:dl2170:iCurr",
                "dl1:sl1:BAS:dl2170:iRunTime",
                "dl1:sl1:BAS:dl2170:iSumFlt"};

        /**
         * 模拟生成nocc数据
         */
        while (true){
            Thread.sleep(1000);
            MessageObj messageObj = new MessageObj();
            messageObj.setFlag(0);
            List<InnerObj> innerObjList = new ArrayList<>();
            for (int i = 0; i < 120; i++) {
                InnerObj innerObj = new InnerObj();
                innerObj.setLabel(labelArr[ToolsUtil.getRandInt()%20]);
                innerObj.setStatus(ToolsUtil.getRandInt()%5);
                if (ToolsUtil.getRandInt()%2 == 1) {
                    innerObj.setType(1);
                    innerObj.setValue("5");
                }else {
                    innerObj.setType(2);
                    innerObj.setValue("5.6");
                }
                //获取秒数
                Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
                innerObj.setTimestamp(second);
                innerObjList.add(innerObj);
            }
            messageObj.setData(innerObjList);

            String msg = JSON.toJSONString(messageObj);

            producer.send(TopicConst.TOPIC_L1_SYS_ATS, msg);
            producer.send(TopicConst.TOPIC_L2_SYS_ATS, msg);
            producer.send(TopicConst.TOPIC_L3_SYS_ATS, msg);
            producer.send(TopicConst.TOPIC_L1_SYS_TMS, msg);
            producer.send(TopicConst.TOPIC_L2_SYS_TMS, msg);
            producer.send(TopicConst.TOPIC_L3_SYS_TMS, msg);



//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,11, msg);
//
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,11, msg);
//
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,7, msg);







//
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,11, msg);
//
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,11, msg);
//
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,11, msg);

//        }

//        while (true) {
//
//            Thread.sleep(500);
//            MessageA messageA = new MessageA();
//            messageA.setFlag(0);
//            messageA.setLabel("dl1:sl1:ACS:dl369");
//            messageA.setStatus(ToolsUtil.getRandInt());
//            messageA.setType(5);
//            messageA.setValue("aa");
//            //获取秒数
//            Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
//            messageA.setTimestamp(second);
//
//            String msg = JSON.toJSONString(messageA);

//            producer.send(TopicConst.TOPIC_L1_SYS_ATS_TEST, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_ATS_TEST, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_ATS_TEST, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_TMS_TEST, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_TMS_TEST, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_TMS_TEST, msg);

//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_EMS_TEST,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_EMS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_EMS,11, msg);
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_BAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_BAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_BAS,11, msg);
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_PSCADA,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_PSCADA,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_PSCADA,11, msg);
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_FAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_FAS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_FAS,11, msg);
//
//
//
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L1_SYS_DTS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L2_SYS_DTS,7, msg);
//
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,0, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,1, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,2, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,3, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,4, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,5, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,6, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,7, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,8, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,9, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,10, msg);
//            producer.send(TopicConst.TOPIC_L3_SYS_DTS,11, msg);

        }
    }

}
