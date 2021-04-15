package com.zsy.kafka.kafkademo.service;

public interface KafkaConsumerListenerService {

    /**
     * 开启监听
     * @param listenerId
     */
    void startListener(String listenerId);

    /**
     * 停止监听
     * @param listenerId
     */
    void stopListener(String listenerId);
}
