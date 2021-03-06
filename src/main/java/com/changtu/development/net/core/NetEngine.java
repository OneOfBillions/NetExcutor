package com.changtu.development.net.core;


import android.content.Context;

import com.changtu.development.net.Canceller;
import com.changtu.development.net.NetResponseInfo;
import com.changtu.development.net.parameter.NetParameter;
import com.changtu.development.net.request.NetRequestConfig;

/**
 * 网络请求引擎，实现类一般是各种网络框架okhttp,httpclient.
 * 范例:{@link OkHttpNetEngine}
 *
 * @version V1.0
 */
public interface NetEngine {
    /**
     * Init context.
     *
     * @param context the context
     */
    void initContext(Context context);

     NetResponseInfo request(NetParameter params, NetRequestConfig.Method method);


    /**
     * Add task tag.
     *
     * @param taskTag the task tag
     */
    void addTaskTag(Object taskTag);

    /**
     * Cancel task.
     *
     * @param canceller the canceller
     */
    void cancelTask(Canceller canceller);


}
