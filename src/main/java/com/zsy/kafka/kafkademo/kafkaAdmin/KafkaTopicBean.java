package com.zsy.kafka.kafkademo.kafkaAdmin;

/**
 * created by yqq 2020/3/25
 */
public class KafkaTopicBean {

    private String topic;

    private Integer partition;

    private Integer replicaton;

    private String describe;

    private Integer operationType;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public Integer getReplicaton() {
        return replicaton;
    }

    public void setReplicaton(Integer replicaton) {
        this.replicaton = replicaton;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}
