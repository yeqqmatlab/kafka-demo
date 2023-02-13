package com.zsy.kafka.kafkademo.rtdb;

import java.util.List;

/**
 * 实时库查询类，封装查询条件必须包含的字段
 */
public class RtdbQueryParam {
    private Integer domainId;
    private String tableName;
    private String condition;
    private int index;
    private int pageSize;
    private List<String> fieldKeys;

    public RtdbQueryParam() {
    }

    public RtdbQueryParam(String tableName) {
        this.tableName = tableName;
    }

    public RtdbQueryParam(String tableName, String condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getFieldKeys() {
        return fieldKeys;
    }

    public void setFieldKeys(List<String> fieldKeys) {
        this.fieldKeys = fieldKeys;
    }
}
