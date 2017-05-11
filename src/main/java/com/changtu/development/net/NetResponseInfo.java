package com.changtu.development.net;

/**
 * 网络请求结果
 *
 * @version V1.0
 *  2016.12.02
 */
public class NetResponseInfo {
    /** Http code error. */
    public static final String HTTP_CODE_ERROR = "-1";

    /**
     * 返回的字符数据
     */
    private String result;
    /**
     * 根据HTTP 返回码确认是否成功
     */
    private boolean isSuccess;
    /**
     * HTTP返回码
     */
    private String httpCode = HTTP_CODE_ERROR;
    /**
     * 网络耗时统计-开始UTC
     */
    private String startTime;
    /**
     * 网络耗时统计-结束UTC
     */
    private String endTime;
    /**
     * 网络请求错误信息
     */
    private String exception;

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Gets http code.
     *
     * @return the http code
     */
    public String getHttpCode() {
        return httpCode;
    }

    /**
     * Sets http code.
     *
     * @param httpCode the http code
     */
    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * Sets exception.
     *
     * @param exception the exception
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
