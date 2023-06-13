package com.zsy.kafka.kafkademo.service;

import com.alibaba.fastjson.JSONObject;
import com.zsy.kafka.kafkademo.pojo.FeatureObj;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @ClassName ApiService
 * @Author yqq
 * @create 2023/6/13 19:02
 */
@FeignClient(name="ApiService", url="http://127.0.0.1:5000/")
public interface ApiService {
    @PostMapping("/predict")
    JSONObject predict(@RequestBody FeatureObj obj);
}
