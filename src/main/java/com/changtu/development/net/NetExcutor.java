package com.changtu.development.net;

import android.content.Context;
import android.util.Log;

import com.changtu.development.net.core.OkHttpNetEngine;
import com.changtu.development.net.listener.NetUIListener;
import com.changtu.development.net.parser.JsonDataParser;
import com.changtu.development.net.request.NetRequestConfig;
import com.changtu.development.net.task.CommonNetTask;
import com.changtu.development.net.tools.NetUtils;

/**
 * The Class CttripNetExcutor.
 *
 * @author 牛翔
 * @version 1.0
 * @date 2015 -11-5
 */
public class NetExcutor {
    /**
     * The Constant TAG.
     */
    public static final String TAG = NetExcutor.class.getSimpleName();

    /**
     * Instantiates a new cttrip net excutor.
     */
    private NetExcutor() {
        super();

    }


    /**
     * <pre>
     *
     * 通用的网络请求实现方法。
     *
     * 网络引擎:OkHttpNetEngine
     *
     * 返回数据解析:JsonDataParser
     *
     * 网络方式:post
     *
     * 网络配置:{@lingk NetOptions.DEFAULT}
     *
     * </pre>
     *
     * @param <T>        the type parameter
     * @param context    the context
     * @param uiListener the ui listener
     * @return the canceller
     */
    public static <T> Canceller executorCommonRequest(final Context context,
                                                      final NetUIListener<T> uiListener) {
        return executorCommonRequest(context, uiListener, NetOptions.DEFAULT);
    }

    /**
     * <pre>
     *
     * 通用的网络请求实现方法。
     *
     * 网络引擎:{@link OkHttpNetEngine}
     *
     * 返回数据解析:{@link JsonDataParser}
     *
     * 网络方式: POST {@link NetRequestConfig.Method}
     *
     *
     * </pre>
     *
     * @param <T>        the type parameter
     * @param context    the context
     * @param uiListener the ui listener
     * @param options    the options
     * @return the canceller
     */
    public static <T> Canceller executorCommonRequest(final Context context,
                                                      final NetUIListener<T> uiListener, final NetOptions
                                                              options) {
        Canceller canceller = new Canceller(null);
        try {
            if (uiListener != null) {
                OkHttpNetEngine engine = new OkHttpNetEngine(context);//网络引擎
                NetRequestConfig.Builder builder = new NetRequestConfig.Builder();//设置请求参数
                builder.setContext(context);
                builder.setNetUIListener(uiListener);
                builder.setOptions(options);
                builder.setNetEngine(engine);
                builder.setMethod(uiListener.getMethod());
                builder.setDataParser(new JsonDataParser());
                canceller = new Canceller(engine);
                new CommonNetTask(builder.build()).start();
            }
        } catch (Exception e) {

            Log.d(NetUtils.TAG, "网络请求错误: " + e.toString());
        }


        return canceller;

    }


    /**
     * @version V1.0
     * @Title: Run mode
     * @date 2016.08.15
     * @Title: Run mode
     * @Title: Run mode
     */
    public enum RunMode {
        /**
         * Current thread.
         */
        CURRENT_THREAD, /**
         * Single thread pool.
         */
        SINGLE_THREAD_POOL, /**
         * Mutiple thread pool.
         */
        MUTIPLE_THREAD_POOL

    }


}
