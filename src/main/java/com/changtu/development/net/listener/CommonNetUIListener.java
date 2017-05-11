package com.changtu.development.net.listener;


import com.changtu.development.net.KeyValuePair;
import com.changtu.development.net.parameter.CommonNetParameter;
import com.changtu.development.net.parameter.NetParameter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public abstract  class CommonNetUIListener<T>  extends NetUIListener<T> implements  ConfigUrl{
    @Override
    public NetParameter createNetParams() {
        return new CommonNetParameter(createUrl(),submitNetParams());
    }





    /**
     * 提交网络请求参数
     * <p/>
     * return NULL，不发送网络请求，也不会有结果回调
     * <p/>
     * UI线程运行
     * <p/>
     *
     * @return the common params bean
     */
    public abstract List<KeyValuePair> submitNetParams();

}
