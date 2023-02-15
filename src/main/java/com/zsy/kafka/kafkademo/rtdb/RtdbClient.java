package com.zsy.kafka.kafkademo.rtdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nrts.iscs.rtdb.api.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * RTDB实时库JAVA接口封装<br>
 * <li>基于Spring容器自动装配IRtdbNodeNotifier、IRtdbTableNotifier、IRtdbCommandResultNotifier、IRtdbExceptionNotifier</li>
 */
public class RtdbClient {

    public static Logger logger = LoggerFactory.getLogger(RtdbClient.class);
    private static RtdbProxy rtdbProxy;
    private static int status = 0;

    static {
        System.out.println(System.getProperty("java.library.path"));
        initProxy();

        createSubscribe(Const.RTDB_REGISTER_SUB_NAME);

//        initSubNotifier();
//
//        initCommandResultNotifier();
//
//        initExceptionNotifier();
    }

    private static void initSubNotifier() {
        Map<String, IRtdbNodeNotifier> rtdbNodeNotifierMap = SpringHelper.getApplicationContext().getBeansOfType(IRtdbNodeNotifier.class);
        Map<String, IRtdbTableNotifier> rtdbTableNotifierMap = SpringHelper.getApplicationContext().getBeansOfType(IRtdbTableNotifier.class);
        IRtdbNodeNotifier rtdbNodeNotifier = null;
        IRtdbTableNotifier rtdbTableNotifier = null;
        if (MapUtils.isNotEmpty(rtdbNodeNotifierMap)) {
            rtdbNodeNotifier = rtdbNodeNotifierMap.values().stream().findFirst().get();
        }
        if (MapUtils.isNotEmpty(rtdbTableNotifierMap)) {
            rtdbTableNotifier = rtdbTableNotifierMap.values().stream().findFirst().get();
        }
        if (null != rtdbNodeNotifier || null != rtdbTableNotifier) {
            setSubNotifier(Const.RTDB_REGISTER_SUB_NAME, rtdbNodeNotifier, rtdbTableNotifier);
        }
    }

    private static void initCommandResultNotifier() {
        Map<String, IRtdbCommandResultNotifier> map = SpringHelper.getApplicationContext().getBeansOfType(IRtdbCommandResultNotifier.class);
        if (MapUtils.isNotEmpty(map)) {
            registerCommandResultNotifier(map.values().stream().findFirst().get());
        }
    }

    private static void initExceptionNotifier() {
        Map<String, IRtdbExceptionNotifier> map = SpringHelper.getApplicationContext().getBeansOfType(IRtdbExceptionNotifier.class);
        if (MapUtils.isNotEmpty(map)) {
            registerException(map.values().stream().findFirst().get());
        }
    }

    public static void initProxy() {
        int result = RtdbProxyEnv.InitEnv();
        if (result < 0) {
            logger.info("实时库环境初始化失败！");
        }

        rtdbProxy = RtdbProxyEnv.CreateRtdbProxy();
        if (rtdbProxy == null) {
            logger.info("实时库连接程序初始化失败！");
            RtdbProxyEnv.UninitEnv();
        }

        result = rtdbProxy.InitRtdbClient(RtdbProxy.RTDBCLI_TYPE_RTDB);
        if (result < 0) {
            logger.info("实时库客户端程序初始化失败！");
            RtdbProxyEnv.DeleteRtdbProxy(rtdbProxy);
            RtdbProxyEnv.UninitEnv();
        }
        logger.info("实时库客户端初始化完成！");
    }

    public static String getUUID() throws RtdbException {
        RtdbVariant variant_cmdid = new RtdbVariant();
        int result = rtdbProxy.GenCmdId(variant_cmdid);
        if (result < 0) {
            logger.error("getUUID错误: result={}", result);
            throw new RtdbException("getUUID错误");
        }
        return toStringValue(variant_cmdid);
    }

    public static void registerService(String serviceName) throws RtdbException {
        int result = rtdbProxy.RegisterService(serviceName);
        if (result < 0) {
            logger.error("registerService错误: serviceName={}, result={}", serviceName, result);
            throw new RtdbException("registerService错误");
        }
    }

    public static void unregisterService(String serviceName) throws RtdbException {
        int result = rtdbProxy.UnregisterService(serviceName);
        if (result < 0) {
            logger.error("unregisterService错误: serviceName={}, result={}", serviceName, result);
            throw new RtdbException("unregisterService错误");
        }
    }

    public static void registerException(IRtdbExceptionNotifier rtdbExceptionNotifier) throws RtdbException {
        int result = rtdbProxy.RegisterExceptionNotifier(rtdbExceptionNotifier, rtdbProxy);
        if (result < 0) {
            logger.error("registerException错误: result={}", result);
            throw new RtdbException("registerException错误");
        }
    }

    public static void unregisterException() throws RtdbException {
        int result = rtdbProxy.RemoveExceptionNotifier();
        if (result < 0) {
            logger.error("unregisterException错误: result={}", result);
            throw new RtdbException("unregisterException错误");
        }
    }

    public static void registerMessageNotifier(IRtdbMessageNotifier rtdbMessageNotifier) throws RtdbException {
        int result = rtdbProxy.RegisterMsgNotifier(rtdbMessageNotifier, rtdbProxy);
        if (result < 0) {
            logger.error("registerMessageNotifier错误: result={}", result);
            throw new RtdbException("registerMessageNotifier错误");
        }
    }

    public static void unregisterMessageNotifier() throws RtdbException {
        int result = rtdbProxy.RemoveMsgNotifier();
        if (result < 0) {
            logger.error("unregisterMessageNotifier错误: result={}", result);
            throw new RtdbException("unregisterMessageNotifier错误");
        }
    }

    public static void registerCommandNotifier(IRtdbCommandNotifier rtdbCommandNotifier) throws RtdbException {
        int result = rtdbProxy.RegisterCmdNotifier(rtdbCommandNotifier, rtdbProxy);
        if (result < 0) {
            logger.error("registerCommandNotifier错误: result={}", result);
            throw new RtdbException("registerCommandNotifier错误");
        }
    }

    public static void unregisterCommandNotifier() throws RtdbException {
        int result = rtdbProxy.RemoveCmdNotifier();
        if (result < 0) {
            logger.error("unregisterCommandNotifier错误: result={}", result);
            throw new RtdbException("unregisterCommandNotifier错误");
        }
    }

    public static void registerCommandResultNotifier(IRtdbCommandResultNotifier rtdbCommandResultNotifier) throws RtdbException {
        int result = rtdbProxy.RegisterCmdResultNotifier(rtdbCommandResultNotifier, rtdbProxy);
        if (result < 0) {
            logger.error("registerCommandResultNotifier错误: result={}", result);
            throw new RtdbException("registerCommandResultNotifier错误");
        }
    }

    public static void unregisterCommandResultNotifier() throws RtdbException {
        int result = rtdbProxy.RemoveCmdResultNotifier();
        if (result < 0) {
            logger.error("unregisterCommandResultNotifier错误: result={}", result);
            throw new RtdbException("unregisterCommandResultNotifier错误");
        }
    }

    public static void sendMessage(int domainId, int type, String destination, String messageInfo) throws RtdbException {
        int result = rtdbProxy.SendMsg(domainId, type, destination, messageInfo.getBytes(), messageInfo.getBytes().length);
        if (result < 0) {
            logger.error("sendMessage错误: domainId={},type={},destination={},messageInfo={},result={}", domainId, type, destination, messageInfo, result);
            throw new RtdbException("sendMessage错误");
        }
    }

    public static void sendCommand(int domainId, int type, String destination, String cmdInfo) throws RtdbException {
        sendCommand(domainId, type, destination, getUUID(), cmdInfo);
    }

    public static void sendCommand(int domainId, int type, String destination, String cmdId, String cmdInfo) throws RtdbException {
        int result = rtdbProxy.SendCommand(domainId, type, destination, cmdId, cmdInfo);
        if (result < 0) {
            logger.error("sendCommand错误: domainId={},type={},destination={},cmdInfo={},result={}", domainId, type, destination, cmdInfo, result);
            throw new RtdbException("sendCommand错误");
        }
    }

    public static void sendCommandResult(int domainId, int type, String destination, String cmdResult, String commandResultId) throws RtdbException {
        if (commandResultId == null) {
            return;
        }
        int result = rtdbProxy.SendCmdResult(domainId, type, destination, commandResultId, 0, cmdResult);
        if (result < 0) {
            logger.error("sendCommandResult错误: domainId={},type={},destination={},command_result_id={},cmdResult={},result={}", domainId, type, destination, cmdResult, cmdResult, result);
            throw new RtdbException("sendCommandResult错误");
        }
    }

    public static void insertTableRecord(int domainId, RtdbRecord rtdbRecord) throws RtdbException {
        int result = rtdbProxy.InsertTblRecord(domainId, rtdbRecord.getTableName(), rtdbRecord.getStrCond(), rtdbRecord.getTableFieldKeys(), rtdbRecord.getTableFieldVals());
        if (result < 0) {
            logger.error("insertTableRecord错误: domainId={},rtdbRecord={},result={}", domainId, rtdbRecord, result);
            throw new RtdbException("insertTableRecord错误");
        }
    }

    public static List<RtdbVariant> queryTblRecord(int domainId, RtdbQueryParam param) throws RtdbException {
        List<RtdbVariant> datas = new ArrayList<>();
        RtdbVariant pageRows = new RtdbVariant(param.getPageSize());
        RtdbVariant totalRows = new RtdbVariant();
        int result = rtdbProxy.GetTblRecord(
                domainId,
                param.getTableName(),
                param.getCondition(),
                param.getFieldKeys(),
                datas,
                pageRows,
                totalRows,
                param.getIndex()
        );
        if (result < 0) {
            logger.error("查询实时库数据错误：domain_id={},param={}", domainId, param);
            throw new RtdbException("查询实时库数据错误");
        }
        return datas;
    }

    public static <T> T getTblRecord(int domainId, RtdbQueryParam param, Class<T> clazz) throws RtdbException {
        List<RtdbVariant> result = queryTblRecord(domainId, param);
        //转换数据模型
        return createDataObj(
                param.getFieldKeys(),
                result.subList(0, param.getFieldKeys().size()),
                clazz);
    }

    public static <T> List<T> listTblRecord(int domainId, RtdbQueryParam param, Class<T> clazz) throws RtdbException {
        List<RtdbVariant> result = queryTblRecord(domainId, param);
        //转换数据模型
        List<T> tList = new ArrayList<>();
        for (int i = 0; i < result.size(); i += param.getFieldKeys().size()) {
            T t = createDataObj(
                    param.getFieldKeys(),
                    result.subList(i, i + param.getFieldKeys().size()),
                    clazz
            );
            tList.add(t);
        }
        return tList;
    }

    private static <T> T createDataObj(List<String> fieldKeys, List<RtdbVariant> rtdbVariants, Class<T> clazz) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < fieldKeys.size(); i++) {
            String key = fieldKeys.get(i);
            String value = toStringValue(rtdbVariants.get(i));
            jsonObject.put(key, value);
        }
        return JSON.toJavaObject(jsonObject, clazz);
    }

    public static List<RtdbVariant> queryTableRecord(int domainId, String tableName, String strCond, List<String> attrs) throws RtdbException {
        RtdbVariant rows = new RtdbVariant();
        RtdbVariant totalRows = new RtdbVariant();
        LinkedList<RtdbVariant> datas = new LinkedList<>();
        int result = rtdbProxy.GetTblRecord(domainId, tableName, strCond, attrs, datas, rows, totalRows, 0);
        if (result < 0) {
            logger.error("queryTableRecord错误: tableName={},strCond={},attrs={},result={}", tableName, strCond, String.join(",", attrs), result);
            throw new RtdbException("queryTableRecord错误");
        }
        return datas;
    }

    public static void updateTableRecord(int domainId, RtdbRecord rtdbRecord) throws RtdbException {
        int result = rtdbProxy.UpdateTblRecord(domainId, rtdbRecord.getTableName(), rtdbRecord.getStrCond(), rtdbRecord.getTableFieldKeys(), rtdbRecord.getTableFieldVals());
        if (result < 0) {
            logger.error("updateTableRecord错误: domainId={},rtdbRecord={},result={}", domainId, rtdbRecord, result);
            throw new RtdbException("updateTableRecord错误");
        }
    }

    public static void deleteTableRecord(int domainId, RtdbQueryParam rtdbQueryParam) throws RtdbException {
        int result = rtdbProxy.DelTblRecord(domainId, rtdbQueryParam.getTableName(), rtdbQueryParam.getCondition());
        if (result < 0) {
            logger.error("deleteTableRecord错误: domainId={},rtdbQueryParam={},result={}", domainId, rtdbQueryParam, result);
            throw new RtdbException("deleteTableRecord错误");
        }
    }

    public static String queryNodeAttr(String path) throws RtdbException {
        RtdbVariant variant_data = new RtdbVariant();
        int result = rtdbProxy.GetNodeUniqAttr(path, variant_data);
        if (result < 0) {
            logger.error("queryNodeAttr错误: path={},result={}", path, result);
            throw new RtdbException("queryNodeAttr错误");
        }
        return toStringValue(variant_data);
    }

    public static <T> T queryNodeRecord(String path, List<String> names, Class<T> clazz) throws RtdbException {
        LinkedList<RtdbVariant> datas = new LinkedList<RtdbVariant>();
        int result = rtdbProxy.GetNodeMultiAttrs(path, names, datas);
        if (result < 0) {
            logger.error("queryNodeRecord错误: path={},names={},result={}", path, String.join(",", names), result);
            throw new RtdbException("queryNodeRecord错误");
        }
        JSONObject jsonObject = new JSONObject();
        datas.forEach(v -> {
            if (StringUtils.isNotEmpty(v.getKey())) {
                jsonObject.put(v.getKey(), toStringValue(v));
            }
        });
        return JSON.toJavaObject(jsonObject, clazz);
    }

    public static <T> List<T> queryNodeRecords(List<String> paths, List<String> names, Class<T> clazz) throws RtdbException {
        LinkedList<RtdbVariant> datas = new LinkedList<RtdbVariant>();
        int result = rtdbProxy.GetMultiNodeMultiAttrs(paths, names, datas);
        if (result < 0) {
            logger.error("queryNodeRecords错误: paths={},names={},result={}", String.join(",", paths), String.join(",", names), result);
            throw new RtdbException("queryNodeRecords错误");
        }
        List<T> tList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i += names.size()) {
            T t = createDataObj(
                    names,
                    datas.subList(i, i + names.size()),
                    clazz
            );
            tList.add(t);
        }
        return tList;
    }

    public static String getNodeType(String path) throws RtdbException {
        RtdbVariant variant_data = new RtdbVariant();
        int result = rtdbProxy.GetNodeType(path, variant_data);
        if (result < 0) {
            logger.error("getNodeType错误: path={},result={}", path, result);
            throw new RtdbException("getNodeType错误");
        }
        return toStringValue(variant_data);
    }

    public static List<String> getSubnodeKey(String path) throws RtdbException {
        LinkedList<String> children = new LinkedList<String>();
        int result = rtdbProxy.GetNodeALLChildren(path, children);
        if (result < 0) {
            logger.error("getSubnodeKey错误: path={},result={}", path, result);
            throw new RtdbException("getSubnodeKey错误");
        }
        return children;
    }

    public static List<String> getNodeAttributeKey(String path) throws RtdbException {
        LinkedList<String> keys = new LinkedList<String>();
        int result = rtdbProxy.GetNodeALLKeys(path, keys);
        if (result < 0) {
            logger.error("GetNodeALLKeys错误: path={},result={}", path, result);
            throw new RtdbException("GetNodeALLKeys错误");
        }
        return keys;
    }

    public static void getSVTC(String path, RtdbVariant data) throws RtdbException {
        int result = rtdbProxy.GetNodeSVTC(path, data);
        if (result < 0) {
            logger.error("getSVTC错误: path={},data={},result={}", path, data, result);
            throw new RtdbException("getSVTC错误");
        }
    }

    public static void setNodeAttribute(String path, String value) throws RtdbException {
        int result = rtdbProxy.SetAttrValue(path, value);
        if (result < 0) {
            logger.error("setNodeAttribute错误: path={},value={},result={}", path, value, result);
            throw new RtdbException("setNodeAttribute错误");
        }
    }

    public static void setNodeStatus(String path, int bit, int value) throws RtdbException {
        int result = rtdbProxy.SetNodeBitVal(path, bit, value);
        if (result < 0) {
            logger.error("setNodeStatus错误: path={},bit={},value={},result={}", path, bit, value, result);
            throw new RtdbException("setNodeStatus错误");
        }
    }

    public static void setSVTC(String path, RtdbVariant data) throws RtdbException {
        int result = rtdbProxy.SetNodeSVTC(path, data);
        if (result < 0) {
            logger.error("setSVTC错误: path={},data={},result={}", path, data, result);
            throw new RtdbException("setSVTC错误");
        }
    }

    public static void createSubscribe(String subName) throws RtdbException {
        int result = rtdbProxy.CreateSubscribe(subName);
        if (result < 0) {
            logger.error("createSubscribe错误: subName={},result={}", subName, result);
            throw new RtdbException("createSubscribe错误");
        }
    }

    public static void setSubNotifier(String subName, IRtdbNodeNotifier rtdbNodeNotifier, IRtdbTableNotifier rtdbTableNotifier) throws RtdbException {
        int result = rtdbProxy.SetSubNotifiers(subName, rtdbNodeNotifier, rtdbProxy, rtdbTableNotifier, rtdbProxy);
        if (result < 0) {
            logger.error("setSubNotifier错误: subName={},result={}", subName, result);
            throw new RtdbException("setSubNotifier错误");
        }
    }

    public static void destorySubscribe(String subName) throws RtdbException {
        rtdbProxy.DestorySubscribe(subName);
    }

    public static void subscribeTable(String subName, int domainId, String tableName) throws RtdbException {
        int result = rtdbProxy.SubscribeTable(subName, domainId, tableName);
        if (result < 0) {
            logger.error("subscribeTable错误: subName={},domainId={},tableName={},result={}", subName, domainId, tableName, result);
            throw new RtdbException("subscribeTable错误");
        }
    }

    public static void subscribeTables(String subName, int domainId, List<String> tableNames) throws RtdbException {
        int result = rtdbProxy.SubscribeTables(subName, domainId, tableNames);
        if (result < 0) {
            logger.error("subscribeTables错误: subName={},domainId={},tableNames={},result={}", subName, domainId, String.join(",", tableNames), result);
            throw new RtdbException("subscribeTables错误");
        }
    }

    public static void unsubscribeTable(String subName, int domainId, String tableName) throws RtdbException {
        int result = rtdbProxy.UnSubscribeTable(subName, domainId, tableName);
        if (result < 0) {
            logger.error("unsubscribeTable错误: subName={},domainId={},tableName={},result={}", subName, domainId, tableName, result);
            throw new RtdbException("unsubscribeTable错误");
        }
    }

    public static void unsubscribeTables(String subName, int domainId, List<String> tableNames) throws RtdbException {
        int result = rtdbProxy.UnSubscribeTables(subName, domainId, tableNames);
        if (result < 0) {
            logger.error("unsubscribeTables错误: subName={},domainId={},tableNames={},result={}", subName, domainId, String.join(",", tableNames), result);
            throw new RtdbException("unsubscribeTables错误");
        }
    }

    public static void subscribeNode(String subName, int domainId, String path, List<String> attrs) throws RtdbException {
        int result = rtdbProxy.SubscribeNode(subName, domainId, path, attrs);
        if (result < 0) {
            logger.error("subscribeNode错误: subName={},domainId={},path={},attrs={},result={}", subName, domainId, path, String.join(",", attrs), result);
            throw new RtdbException("subscribeNode错误");
        }
    }

    public static void subscribeNodes(String subName, int domainId, List<String> paths, List<String> attrs) throws RtdbException {
        int result = rtdbProxy.SubscribeNodes(subName, domainId, paths, attrs);
        if (result < 0) {
            logger.error("subscribeNodes错误: subName={},domainId={},paths={},attrs={},result={}", subName, domainId, String.join(",", paths), String.join(",", attrs), result);
            throw new RtdbException("subscribeNodes错误");
        }
    }

    public static void unsubscribeNode(String subName, int domainId, String path) throws RtdbException {
        int result = rtdbProxy.UnSubscribeNode(subName, domainId, path);
        if (result < 0) {
            logger.error("unsubscribeNode错误: subName={},domainId={},path={},result={}", subName, domainId, path, result);
            throw new RtdbException("unsubscribeNode错误");
        }
    }

    public static void unsubscribeNodes(String subName, int domainId, List<String> paths) throws RtdbException {
        int result = rtdbProxy.UnSubscribeNodes(subName, domainId, paths);
        if (result < 0) {
            logger.error("unsubscribeNodes错误: subName={},domainId={},paths={},result={}", subName, domainId, String.join(",", paths), result);
            throw new RtdbException("unsubscribeNodes错误");
        }
    }

    /**
     * 实时库数据类型转字符串，浮点数保留2位小数
     *
     * @param rtdbVariant
     * @return
     */
    public static String toStringValue(RtdbVariant rtdbVariant) {
        if (rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_BOOL) {
            //布尔值直接转为字面值
            return String.valueOf(rtdbVariant.toBool());
        } else if (
                rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_INT8 ||
                        rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_INT16 ||
                        rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_INT32 ||
                        rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_INT64
        ) {
            //整型按长整型返回
            return String.valueOf(rtdbVariant.toLong());
        } else if (
                        rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_DOUBLE
        ) {
            //浮点数
            return String.valueOf(rtdbVariant.toDouble());
        } else if (
                rtdbVariant.getType() == RtdbVariant.RTDB_ATTR_FLOAT
        ) {
            //浮点数
            return String.valueOf(rtdbVariant.toFloat());
        } else {
            return rtdbVariant.toString();
        }
    }

    public static void main(String[] args) throws IOException {

//        RtdbQueryParam rtdbQueryParam = new RtdbQueryParam("linkage");
//        rtdbQueryParam.setIndex(0);
//        rtdbQueryParam.setPageSize(10);
//        rtdbQueryParam.setCondition("linkage_id='LNK_001'");
//        rtdbQueryParam.setFieldKeys(Arrays.asList("des"));
//        rtdbQueryParam.setDomainId(1);
//        List<RtdbVariant> list = queryTblRecord(1, rtdbQueryParam);
//        System.out.println("-->");
//
//        Map map1 = RtdbClient.queryNodeRecord("dgj:dgj:PSCADA:1500KX_214:Imax++", Arrays.asList("ai_value", "di_value", "acc_value"), Map.class);
//        System.out.println(JSON.toJSONString(map1));
//
//        System.out.println("========================================");
//        List<Map> list = RtdbClient.queryNodeRecords(Arrays.asList("dgj:dgj:PSCADA:1500KX_211:BUSVOLT"), Arrays.asList("camera_tag", "update_time", "ai_value", "di_value", "acc_value"), Map.class);
//        list.forEach(map -> {
//            System.out.println(JSON.toJSONString(map));
//        });
//        System.out.println("========================================");
//        System.in.read();

//        System.out.println(queryNodeAttr("dl1:sl1:BAS:dl6192:i1RunTime.ai_value"));
//
//// list
//        RtdbQueryParam rtdbQueryParam = new RtdbQueryParam();
//        rtdbQueryParam.setDomainId(1);
//        rtdbQueryParam.setTableName("fes_group");
//        rtdbQueryParam.setCondition("group_label='test01'");
//        rtdbQueryParam.setFieldKeys(Arrays.asList("group_label",
//                "group_tag",
//                "group_id",
//                "domain_id",
//                "station_id",
//                "pro_system_id",
//                "group_desc",
//                "run_mode",
//                "redundancy_port",
//                "cli_tool_port",
//                "para3",
//                "para4",
//                "crc32_code"));
//        rtdbQueryParam.setIndex(0);
//        rtdbQueryParam.setPageSize(1);
//        List<Map> list = listTblRecord(1, rtdbQueryParam, Map.class);
//        System.out.println("");
//
//// add
//        RtdbRecord vo = new RtdbRecord();
//        vo.setTableName("fes_group");
//        vo.setTableFieldKeys(Arrays.asList(
//                "group_label", "group_tag", "group_id", "domain_id",
//                "station_id", "pro_system_id", "group_desc", "run_mode",
//                "redundancy_port", "cli_tool_port", "para3", "para4",
//                "crc32_code"
//        ));
//        vo.setTableFieldVals(Arrays.asList(
//                new RtdbVariant("test01"), new RtdbVariant("test01"), new RtdbVariant(876), new RtdbVariant(1),
//                new RtdbVariant(1), new RtdbVariant(1), new RtdbVariant("test01"), new RtdbVariant(2),
//                new RtdbVariant(8888), new RtdbVariant(7777), new RtdbVariant("par333"), new RtdbVariant("par444"),
//                new RtdbVariant(3467827869L)
//        ));
//        insertTableRecord(1, vo);
//
//// update
//        RtdbRecord vo2 = new RtdbRecord();
//        vo2.setTableName("fes_group");
//        vo2.setStrCond("group_label='test01'");
//        vo2.setTableFieldKeys(Arrays.asList(
//                "group_desc"
//        ));
//        vo2.setTableFieldVals(Arrays.asList(
//                new RtdbVariant("test" + "009")
//        ));
//        updateTableRecord(1, vo2);

// delete
        RtdbQueryParam rtdbQueryParam2 = new RtdbQueryParam();
        rtdbQueryParam2.setDomainId(1);
        rtdbQueryParam2.setTableName("fes_group");
        rtdbQueryParam2.setCondition("group_label='test01'");
        deleteTableRecord(1, rtdbQueryParam2);

    }
}
