package com.zsy.kafka.kafkademo.rtdb;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Const {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");

    public static final String REQ_KEY_CLIENTID = "clientId";
    public static final String REQ_KEY_TIMESTAMP = "timestamp";
    public static final String REQ_KEY_SIGN = "sign";

    public static final Integer CMD_TYPE_REMOTE_CONTROL_SELECT = 2100;// 遥控选择
    public static final Integer CMD_TYPE_REMOTE_CONTROL_EXECUTE = 2102;// 遥控执行
    public static final Integer CMD_TYPE_REMOTE_CONTROL_MODE = 2109;// 模式控制

    public static final Integer SIGNAL_TYPE_DI = 1;
    public static final Integer SIGNAL_TYPE_AI = 2;

    public static final String KEY_DI_VALUE = "di_value";
    public static final String KEY_AI_VALUE = "ai_value";
    public static final String KEY_ACC_VALUE = "acc_value";

    public static final String DEFAULT_USER_GROUP_NAME = "admin";
    public static final String DEFAULT_USER_NAME = "admin";
    public static final String DEFAULT_HOST_NAME = "admin";

    public static final String RTDB_REGISTER_SUB_NAME = "ISCS_WEB_API";

    public static Map<String, String> PLUGIN_SVR_MAP = new HashMap() {{
        put(CONTROL_TYPE_PA, "PaSvr");
        put(CONTROL_TYPE_PIS, "PisSvr");
    }};

    public static final String SEND_COMMAND_DESTINATION_RTDB = "rtdb";

    public static final Integer SEND_COMMAND_TYPE_RTDB = 1;
    public static final Integer SEND_COMMAND_TYPE_PLUGIN = 2;
    public static final Integer SEND_COMMAND_DEFAULT_TIMEOUT = 60;
    public static final Integer SEND_COMMAND_TIME_INTERVAL = 5;

    public static final String CONTROL_TYPE_SELFCHECK = "0";
    public static final String CONTROL_TYPE_NODE = "1";
    public static final String CONTROL_TYPE_MODE = "2";
    public static final String CONTROL_TYPE_PA = "3";
    public static final String CONTROL_TYPE_PIS = "4";

    public static Integer LOCAL_DOMAIN_ID = null;
    public static Integer OCC_DOMAIN_ID = 99;

    public static String USER_SETTING_FILENAME = "userSetting.properties";

    // 1-专业告警
    // 2-当日能耗总数，3-当日能耗分项，4-当日能耗分时，5-当年能耗分月，
    // 6-全线客流总数，7-当日进站，8-当日出站，9-当年客流分月,10-当日分站客流
    public static Integer STAT_TYPE_ALARM_PROSYSTEM = 1;
    public static Integer STAT_TYPE_ENEYGY_TODAY = 2;
    public static Integer STAT_TYPE_ENERGY_CAT = 3;
    public static Integer STAT_TYPE_ENERGY_HOUR = 4;
    public static Integer STAT_TYPE_ENERGY_MONTH = 5;
    public static Integer STAT_TYPE_FLOW_TOTAL = 6;
    public static Integer STAT_TYPE_FLOW_IN = 7;
    public static Integer STAT_TYPE_FLOW_OUT = 8;
    public static Integer STAT_TYPE_FLOW_MONTH = 9;
    public static Integer STAT_TYPE_FLOW_STATION = 10;
    public static Integer STAT_TYPE_FLOW_HOUR = 11;

    public static Map<String, String> PRO_SYSTEM_MAP = new HashMap() {{
        put("PSCADA", "电力系统");
        put("BAS", "环控系统");
        put("FAS", "火灾系统");
        put("AFC", "售检票");
        put("ATS", "信号系统");
        put("CCTV", "视频系统");
        put("PA", "广播系统");
        put("PIS", "乘客信息");
        put("PSD", "屏蔽门");
        put("TELALM", "集中告警");
        put("ACS", "门禁系统");
    }};

}
