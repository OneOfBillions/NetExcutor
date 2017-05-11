package com.changtu.development.net.listener;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

import com.changtu.development.net.parameter.NetParameter;
import com.changtu.development.net.request.NetRequestConfig;
import com.changtu.development.net.tools.NetUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @version V1.0
 *  2016.12.05
 */
public abstract  class NetUIListener<T> {

    /**
     * M type.
     */
    private Type mType;
    /**
     * Original data.
     */
    private String originalData;

    /**
     * Gets original data.
     *
     * @return the original data
     */
    public String getOriginalData() {
        return originalData;
    }

    /**
     * Sets original data.
     *
     * @param originalData the original data
     */
    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    /**
     * Instantiates a new Ui listener.
     */
    public NetUIListener() {
        mType = getSuperclassTypeParameter(getClass());

    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return mType;
    }


    /**
     * Gets superclass type parameter.
     *
     * @param subclass the subclass
     * @return the superclass type parameter
     */
    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return parameterized.getActualTypeArguments()[0];
    }

    public NetRequestConfig.Method getMethod(){
        return NetRequestConfig.Method.POST;
    }



    public abstract NetParameter createNetParams();
    /**
     * 在非主线程中组装参数适用于耗时的任务
     */
    public void assembleCommonParamsBeanBackground(){

    }

    /**
     * SUCCESS:服务器成功返回数据并GSON解析完成，业务处理
     * SERVER_ERROR:系统错误(例如gson解析错误)
     * NET_ERROR:网络错误
     * UI线程运行
     *
     * @param bean             the bean
     * @param netRequestStatus the net request status
     */
    public abstract void onComplete(T bean, NetUtils.NetRequestStatus netRequestStatus);

}
