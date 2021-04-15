package com.zsy.kafka.kafkademo.controller;

import com.zsy.kafka.kafkademo.service.KafkaConsumerListenerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/listener")
public class KafkaConsumerListenerController {

    /**
     * LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerListenerController.class);

    /**
     * 注入监听服务.
     */
    @Autowired
    private KafkaConsumerListenerServiceImpl kafkaConsumerListenerService;


    /**
     * 开启监听.
     *
     * @param listenerId 监听ID
     */
    @RequestMapping("/start/{listenerId}")
    public Map<String, String> startListener(@PathVariable("listenerId") String listenerId) {
        if (LOG.isInfoEnabled()) {
            LOG.info("开启监听...listenerId=" + listenerId);
        }

        Map<String, String> retMap = new HashMap<>();
        try {
            kafkaConsumerListenerService.startListener(listenerId);
            retMap.put("respCode", "0000");
            retMap.put("respMsg", "启动成功。");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            retMap.put("respCode", "0001");
            retMap.put("respMsg", "启动失败：" + e.getMessage());
        }
        return retMap;
    }

    /**
     * 停止监听.
     *
     * @param listenerId 监听ID
     */
    @RequestMapping("/stop/{listenerId}")
    public Map<String, String> stopListener(@PathVariable("listenerId") String listenerId) {
        if (LOG.isInfoEnabled()) {
            LOG.info("停止监听...listenerId=" + listenerId);
        }

        Map<String, String> retMap = new HashMap<>();
        try {
            kafkaConsumerListenerService.stopListener(listenerId);
            retMap.put("respCode", "0000");
            retMap.put("respMsg", "停止成功。");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            retMap.put("respCode", "0001");
            retMap.put("respMsg", "停止失败：" + e.getMessage());
        }
        return retMap;
    }



}
