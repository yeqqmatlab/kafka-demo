package com.zsy.kafka.kafkademo.utils;

import java.util.Random;
import java.util.UUID;

public class ToolsUtil {

    public synchronized static String getNextCode() {
        return UUID.randomUUID().toString();
    }

    public synchronized static Float getRandFloat() {
        Random rand = new Random();
        float fee = rand.nextFloat()*100;
        return fee;
    }
}
