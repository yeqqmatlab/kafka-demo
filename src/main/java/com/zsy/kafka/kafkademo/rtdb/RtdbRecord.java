package com.zsy.kafka.kafkademo.rtdb;

import com.nrts.iscs.rtdb.api.RtdbVariant;

import java.util.ArrayList;
import java.util.List;

/**
 * 实时库表，数据插入数据模型
 */
public class RtdbRecord {
    //表名称
    private String tableName;
    //条件
    private String strCond;
    //数据字段名称
    private List<String> tableFieldKeys;
    //数据字段值
    private List<RtdbVariant> tableFieldVals;

    public RtdbRecord() {
        this("", "");
    }

    public RtdbRecord(String tableName) {
        this(tableName, "");
    }

    public RtdbRecord(String tableName, String strCond) {
        this.tableName = tableName;
        this.strCond = strCond;
        this.tableFieldKeys = new ArrayList<>();
        this.tableFieldVals = new ArrayList<>();
    }


    public RtdbRecord addTableFiled(String tableFiledKey, RtdbVariant tableFieldVal) {
        this.tableFieldKeys.add(tableFiledKey);
        this.tableFieldVals.add(tableFieldVal);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableFieldKeys.size(); i++) {
            String tpl = "%s : %s\n";
            RtdbVariant variant = this.tableFieldVals.get(i);
            if (variant.getType() == RtdbVariant.RTDB_ATTR_DOUBLE) {
                sb.append(String.format(tpl, tableFieldKeys.get(i), variant.toDouble()));
            } else if (variant.getType() == RtdbVariant.RTDB_ATTR_INT32 || variant.getType() == RtdbVariant.RTDB_ATTR_INT64) {
                sb.append(String.format(tpl, tableFieldKeys.get(i), variant.toInt()));
            } else if (variant.getType() == RtdbVariant.RTDB_ATTR_STRING) {
                sb.append(String.format(tpl, tableFieldKeys.get(i), variant.toString()));
            }
        }
        return sb.toString();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getTableFieldKeys() {
        return tableFieldKeys;
    }

    public String getStrCond() {
        return strCond;
    }

    public void setStrCond(String strCond) {
        this.strCond = strCond;
    }

    public void setTableFieldKeys(List<String> tableFieldKeys) {
        this.tableFieldKeys = tableFieldKeys;
    }

    public List<RtdbVariant> getTableFieldVals() {
        return tableFieldVals;
    }

    public void setTableFieldVals(List<RtdbVariant> tableFieldVals) {
        this.tableFieldVals = tableFieldVals;
    }
}
