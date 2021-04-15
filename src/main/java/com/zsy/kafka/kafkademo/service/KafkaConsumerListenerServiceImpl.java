package com.zsy.kafka.kafkademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListenerServiceImpl implements KafkaConsumerListenerService{

    /**
     * LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerListenerServiceImpl.class);

    /**
     * registry.
     */
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Override
    public void startListener(String listenerId) {

        //判断监听容器是否启动，未启动则将其启动
        if (!registry.getListenerContainer(listenerId).isRunning()) {
            registry.getListenerContainer(listenerId).start();
        }
        //项目启动的时候监听容器是未启动状态，而resume是恢复的意思不是启动的意思
        registry.getListenerContainer(listenerId).resume();
        LOG.info(listenerId + "开启监听成功。");

    }

    @Override
    public void stopListener(String listenerId) {

        registry.getListenerContainer(listenerId).stop();
        LOG.info(listenerId + "停止监听成功。");

    }
}
