package com.changtu.development.net.core;


import android.content.Context;

import com.changtu.development.net.Canceller;
import com.changtu.development.net.NetResponseInfo;
import com.changtu.development.net.parameter.NetParameter;

/**
 * 网络请求引擎，实现类一般是各种网络框架okhttp,httpclient.
 * 范例:{@link OkHttpNetEngine}
 *
 * @version V1.0
 * @date 2016.12.02
 */
public interface NetEngine {
    /**
     * Init context.
     *
     * @param context the context
     */
    void initContext(Context context);

    /**
     * Request by post net response info.
     *
     * @param params the params
     * @return the net response info
     */
    NetResponseInfo requestByPost(NetParameter params);

    /**
     * Request by get net response info.
     *
     * @param params the params
     * @return the net response info
     */
    NetResponseInfo requestByGet(NetParameter params);

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
