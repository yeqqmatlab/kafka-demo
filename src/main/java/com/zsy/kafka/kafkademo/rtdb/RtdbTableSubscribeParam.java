package com.zsy.kafka.kafkademo.rtdb;

import lombok.Data;

import java.util.List;

@Data
public class RtdbTableSubscribeParam {
    private Integer domainId;
    private List<String> tableNames;
}
