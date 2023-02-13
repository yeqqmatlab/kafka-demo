package com.zsy.kafka.kafkademo.rtdb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RtdbSubscribeData {
    private List<String> paths;
    private List<String> attrs;
}
