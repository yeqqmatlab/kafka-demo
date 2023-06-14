package com.zsy.kafka.kafkademo.pojo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 波型数据 2:未知 1:正常 0:异常
 * @ClassName FeatureObj
 * @Author yqq
 * @create 2023/6/13 20:17
 */
public class FeatureObj implements Serializable {

    private Float[] feature;

    private int label = 2;

    public Float[] getFeature() {
        return feature;
    }

    public void setFeature(Float[] feature) {
        this.feature = feature;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "FeatureObj{" +
                "feature=" + Arrays.toString(feature) +
                ", label=" + label +
                '}';
    }
}
