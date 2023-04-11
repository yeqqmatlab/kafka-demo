package com.zsy.kafka.kafkademo.message;

/**
 * @ClassName InnerObj
 * @Author yqq
 * @create 2023/3/13 16:54
 */
public class InnerObj {

    private String label;

    private int type;

    private Object value;

    private int status;

    private long timestamp;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InnerObj{" +
                "label='" + label + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
