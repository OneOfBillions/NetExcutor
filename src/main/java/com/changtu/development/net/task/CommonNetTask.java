package com.changtu.development.net.task;


import com.changtu.development.net.NetException;
import com.changtu.development.net.NetResponseInfo;
import com.changtu.development.net.core.NetEngine;
import com.changtu.development.net.listener.NetUIListener;
import com.changtu.development.net.request.NetRequestConfig;
import com.changtu.development.net.tools.NetUtils;

import java.lang.reflect.Type;

/**
 * 通用的网络任务.
 *
 * @version V1.0
 *  2016.12.07
 */
public class CommonNetTask extends NetTask {
    /**
     * Instantiates a new Common net task.
     *
     * @param netRequest the net request
     */
    public CommonNetTask(NetRequestConfig netRequest) {
        super(netRequest);
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        Object responseBean = null;
        NetUtils.NetRequestStatus netRequestStatus = NetUtils.NetRequestStatus.SERVER_ERROR;
        NetEngine engine = getNetEngine();


        NetRequestConfig netRequest = getNetRequest();
        NetResponseInfo netResponseInfo;
        NetRequestConfig.Method method = netRequest.getMethod();
        if (method != null) {
            if (method == NetRequestConfig.Method.GET) {
                netResponseInfo = engine.requestByGet
                        (getParameter());
            } else {
                netResponseInfo = engine.requestByPost
                        (getParameter());
            }
        } else {
            throw new NetException("未指定请求方法 eg: post  get ");
        }


        String resultStr = null;
        NetUIListener netUIListener = getNetUiListener();
        Type type = netUIListener.getType();
        if (netResponseInfo != null && netResponseInfo.isSuccess()) {
            resultStr = netResponseInfo.getResult();
            netUIListener.setOriginalData(resultStr);//保存原始字符
            responseBean = netRequest.getDataParser().parse(resultStr, type);
            if (responseBean != null) {
                netRequestStatus = NetUtils.NetRequestStatus.SUCCESS;
            } else {
                netRequestStatus = NetUtils.NetRequestStatus.SERVER_ERROR;
            }
        } else {
            netRequestStatus = NetUtils.NetRequestStatus.NET_ERROR;
        }
        callback(netRequestStatus, responseBean);
    }
}
