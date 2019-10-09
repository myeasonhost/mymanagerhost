package com.eason.transfer.openapi.core.api.utils;

/**
 * 100：参数异常 200：业务异常 300：服务器异常
 *
 * @author eason
 */
public class MessageConstant {
    /**
     * 对象存在
     */
    public static final Integer OBJECT_EXIST = 0;
    /**
     * 对象不存在
     */
    public static final Integer OBJECT_INEXISTENT = 1;
    /**
     * 有没有被修改 0是没有修改
     */
    public static final Integer OBJECT_NOT_UPDATE = 0;
    /**
     * 有木有被修改，1为修改过
     */
    public static final Integer OBJECT_UPDATE = 1;
    /**
     * 系统级参数
     */
    public static String[] SYSTEM_PARAM_ARRAY = new String[]{"timestamp", "method", "ver", "sign"};
    /**
     * timestamp
     */
    public static final String STRING_TIMESTAMP = "timestamp";
    /**
     * 返回数据格式 xml/json
     */
    public static final String STRING_FORMAT = "format";
    /**
     * 验证码
     */
    public static final String STRING_SIGN = "sign";
    /**
     * 版本号
     */
    public static final String STRING_VER = "ver";
    /**
     * 调用的接口名称
     */
    public static final String STRING_METHOD = "method";
    /**
     * 选择显示字段
     */
    public static final String STRING_FIELDS = "fields";
    /**
     * appKey
     */
    public static final String STRING_APPKEY = "appKey";
    /**
     * sessionKey
     */
    public static final String STRING_SESSIONKEY = "sessionKey";
    public static final Integer FIVE = 5;

    /**
     * 检测是否需要强制更新
     *
     * @author lishun
     * @create Date 2015-06-15
     */
    public interface CHECK_UPDATE {

        public static final String METHOD = "eason.app.update.check_";
        /**
         * 设备类型为空
         */
        public static final String ERROR_101 = "101";
        /**
         * app版本号为空
         */
        public static final String ERROR_102 = "102";
        /**
         * appKey为空
         */
        public static final String ERROR_103 = "103";
        /**
         * 设备类型不匹配
         */
        public static final String ERROR_104 = "104";
        /**
         * 未找到对应的app信息
         */
        public static final String ERROR_201 = "201";
        /**
         * 系统错误
         */
        public static final String ERROR_300 = "300";
    }
}
