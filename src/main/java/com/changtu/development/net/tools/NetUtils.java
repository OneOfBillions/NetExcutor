package com.changtu.development.net.tools;

/**
 * 网络请求工具类.
 *
 * @author OneOfBillions
 * @version 1.0
 *  2015 -4-23
 */
public class NetUtils {

    /**
     * The DEBUG TAG.
     */
    public static final String TAG = NetUtils.class.getSimpleName();
    /**
     * 网络请求返回成功标志 0.
     */
    public static final String RESULT_SUCCESS = "0";
    /**
     * The Constant RESULT_SUCCESS.
     */
    public static final String TRUE_FLAG_VALUE_ONE = "1";
    /**
     * The Constant RESULT_SUCCESS.
     */
    public static final String FALSE_FLAG_VALUE_NO = "N";
    /**
     * The Constant RESULT_SUCCESS.
     */
    public static final String FALSE_FLAG_VALUE_FALSE = "false";
    /**
     * The Constant TRUE_FLAG_VALUE_TRUE.
     */
    public static final String TRUE_FLAG_VALUE_TRUE = "true";
    /**
     * The Constant RESULT_SUCCESS.
     */
    public static final String TRUE_FLAG_VALUE_YES = "Y";
    /** True flag value zero. */
    public static final String TRUE_FLAG_VALUE_ZERO = "0";

    /**
     * The Constant LOG_INPUTSTREMURL.
     */
    public static final String LOG_INPUTSTREMURL = "读取URL流:";
    /**
     * The Constant LOG_PARAMS.
     */
    public static final String LOG_PARAMS = "上传参数：";

    /**
     * The Constant LOG_SERVER_RETURN.
     */
    public static final String LOG_SERVER_RETURN = "服务器返回数据:\\\\\\";

    /**
     * The Constant LOG_NET_ERROR.
     */
    public static final String LOG_NET_ERROR = "网络错误";
    /**
     * The Constant LOG_HTTP_STATUS_CODE.
     */
    public static final String LOG_HTTP_STATUS_CODE = "HTTP_STATUS_CODE:";

    /**
     * The Constant LOG_REQUEST_SEQUENCE_NUMBER.
     */
    public static final String LOG_REQUEST_SEQUENCE_NUMBER = "请求编号:";
    /**
     * The Constant LOG_ENCODE_GET_URL.
     */
    private static final String LOG_ENCODE_GET_URL = "拼装GET请求URL";
    /**
     * The Constant LOG_BLANK.
     */
    public static final String LOG_BLANK = "   ";
    /**
     * 错误提示.
     */
    public final static String responseError = "抱歉！数据返回错误，请重试";
    /**
     * The Constant responseDisplayError.
     */
    public final static String responseDisplayError = "前方遇到障碍，正在清理！ 再试试看";
    /**
     * The Constant responseExceptionError.
     */
    public final static String responseExceptionError = "很抱歉！网络出现问题，点击重试";
    /**
     * the constant responsedisplaynotdataerror.
     */
    public final static String responseDisplayNotDataError = "抱歉！暂时还没有";
    /**
     * The Constant responseNetError.
     */
    public final static String responseNetError = "当前网络不可用，请检查或稍后再试";


    /**
     * The Enum OrderButtonOperation.
     *
     * @author OneOfBillions
     * @version 1.0
     *  2015 -8-28
     */
    public enum NetRequestStatus {
        /**
         * 请求成功.
         */
        SUCCESS("SUCCESS"),
        /**
         * The no more data.
         */
        NO_MORE_DATA("没有更多数据"),

        /**
         * Parse error.
         */
        PARSE_ERROR("数据解析错误"),


        /**
         * The net error.
         */
        NET_ERROR(responseNetError),
        /**
         * The server error.
         */
        SERVER_ERROR(responseDisplayError),

        /**
         * The display server error info.
         */
        DISPLAY_SERVER_ERROR_INFO(responseDisplayError);


        /**
         * The note.
         */
        private String note;

        /**
         * Instantiates a new net request status.
         *
         * @param note the note
         */
        NetRequestStatus(String note) {

            this.note = note;

        }

        /**
         * Gets the note.
         *
         * @return the note
         */
        public String getNote() {
            return note;
        }

    }

}
