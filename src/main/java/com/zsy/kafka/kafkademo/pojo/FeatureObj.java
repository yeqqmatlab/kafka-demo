package com.zsy.kafka.kafkademo.pojo;

import java.io.Serializable;

/**
 * @ClassName FeatureObj
 * @Author yqq
 * @create 2023/6/13 20:17
 */
public class FeatureObj implements Serializable {

    private Float[] feature;

    public Float[] getFeature() {
        return feature;
    }

    public void setFeature(Float[] feature) {
        this.feature = feature;
    }
}
