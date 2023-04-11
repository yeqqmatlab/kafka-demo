package com.zsy.kafka.kafkademo.utils;

import java.util.Random;
import java.util.UUID;

public class ToolsUtil {

    public synchronized static String getNextCode() {
        return UUID.randomUUID().toString();
    }

    public synchronized static Integer getRandInt() {
        Random rand = new Random();
        Integer fee = rand.nextInt(100);
        return fee;
    }
}
