package com.zsy.kafka.kafkademo.message;

import java.util.List;

/**
 * @ClassName MessageObj
 * @Author yqq
 * @create 2023/3/13 16:52
 */
public class MessageObj {

    private int flag;

    private List<InnerObj> data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<InnerObj> getData() {
        return data;
    }

    public void setData(List<InnerObj> data) {
        this.data = data;
    }
}
