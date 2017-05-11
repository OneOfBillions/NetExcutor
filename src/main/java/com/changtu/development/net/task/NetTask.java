package com.changtu.development.net.task;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.changtu.development.net.NetException;
import com.changtu.development.net.NetOptions;
import com.changtu.development.net.core.NetEngine;
import com.changtu.development.net.listener.NetUIListener;
import com.changtu.development.net.parameter.NetParameter;
import com.changtu.development.net.parser.NetDataParser;
import com.changtu.development.net.request.NetRequestConfig;
import com.changtu.development.net.tools.GsonUtil;
import com.changtu.development.net.tools.NetUtils;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/6 0006.
 *
 * @version V1.0
 *  2016.12.07
 */
public abstract class NetTask implements Runnable {


    /**
     * 任务处理中的数据中转.
     */
    private HashMap<String, Object> data = new HashMap<>();
    /**
     * 单线程任务池.
     */
    private static ExecutorService executorService = Executors
            .newSingleThreadExecutor();
    /**
     * 多线程任务池.
     */
    private static ExecutorService executorMutipleService = Executors
            .newCachedThreadPool();

    /**
     * 单线程池，适用于时序要求的任务队列.
     *
     * @param runnable the runnable
     */
    public static void executeRunnbale(Runnable runnable) {
        if (runnable != null) {
            executorService.execute(runnable);
        }

    }

    /**
     * 网络请求的配置类.
     */
    private NetRequestConfig netRequest;
    /**
     * Parameter.
     */
    private NetParameter parameter;

    /**
     * Instantiates a new Net task.
     *
     * @param netRequest the net request
     */
    public NetTask(NetRequestConfig netRequest) {
        this.netRequest = netRequest;
        if (netRequest != null) {
            if (netRequest.getNetEngine() != null) {

                if (netRequest.getOptions() != null) {
                    if (netRequest.getNetUIListener() != null) {
                        prepareCreateParameter();
                        this.parameter = netRequest.getNetUIListener().createNetParams();
                        if (this.parameter != null) {
                            //init success
                        } else {
                            throw new NetException("parameter 不能为空");
                        }
                    } else {
                        throw new NetException("uiListener 不能为空");
                    }
                } else {
                    throw new NetException("option 不能为空");
                }
            } else {
                throw new NetException("请设置一种执行网络任务的引擎");
            }


        } else {
            throw new NetException("NetRequest 不能为空");
        }
    }

    /**
     * Prepare create parameter.
     */
    public void prepareCreateParameter() {

    }

    /**
     * Use cache refresh ui.
     *
     * @param <T>        the type parameter
     * @param localValue the local value
     */
    public <T> void callback(String localValue) {

        if (!TextUtils.isEmpty(localValue)) {
            // 得到缓存数据


            T localResponseBean = GsonUtil.fromJsonStringToCollection(
                    localValue, getNetUiListener().getType());

            if (localResponseBean != null) {

                // 用缓存数据先刷新界面
                callback(
                        NetUtils.NetRequestStatus.SUCCESS, localResponseBean);

            }


        }

    }

    /**
     * 网络请求回调
     *
     * @param <T>              the type parameter
     * @param netRequestStatus the net request status
     * @param bean             the bean
     */
    public <T> void callback(
            final NetUtils.NetRequestStatus netRequestStatus, final T bean) {

        if (getNetUiListener() != null) {

            if (getOptions().isPostUIThread()) {
                //回调到UI线程

                if (getContext() != null && getContext() instanceof Activity) {
                    Activity activity = (Activity) getContext();
                    if (!activity.isFinishing()) {
                        boolean isDes = false;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            isDes = activity.isDestroyed();
                        }
                        if (!isDes) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getNetUiListener().onComplete(bean, netRequestStatus);
                                }
                            });


                        }
                    }


                }

            } else {
                //在任务执行线程运行回调
                getNetUiListener().onComplete(bean, netRequestStatus);
            }


        }


    }


    /**
     * 多线程池，适用于并行任务的执行.
     *
     * @param runnable the runnable
     */
    public static void executeMutipleRunnbale(Runnable runnable) {
        if (runnable != null) {
            executorMutipleService.execute(runnable);
        }

    }

    /**
     * Put handler info.
     *
     * @param key   the key
     * @param value the value
     */
    public void putHandlerInfo(String key, Object value) {
        if (!TextUtils.isEmpty(key)) {
            data.put(key, value);
        }

    }


    /**
     * Gets handler info.
     *
     * @param <T>      the type parameter
     * @param key      the key
     * @param classOfT the class of t
     * @return the handler info
     */
    public <T> T getHandlerInfo(String key, Class<T> classOfT) {
        if (!TextUtils.isEmpty(key) && classOfT != null) {
            Object object = data.get(key);
            if (object != null && classOfT.isInstance(object)) {
                return (T) object;


            }


        }
        return null;

    }

    /**
     * Start.
     */
    public void start() {
        NetOptions netOptions = getOptions();
        if (netOptions != null) {
            switch (netOptions.getRunMode()) {
                case SINGLE_THREAD_POOL:
                    executeRunnbale(this);
                    break;
                case MUTIPLE_THREAD_POOL:
                    executeMutipleRunnbale(this);
                    break;
                case CURRENT_THREAD:
                    this.run();
                    break;


            }
        }
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public NetOptions getOptions() {
        return netRequest.getOptions();
    }


    /**
     * Gets net engine.
     *
     * @return the net engine
     */
    public NetEngine getNetEngine() {
        return netRequest.getNetEngine();
    }


    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return netRequest.getContext();
    }


    /**
     * Gets net request.
     *
     * @return the net request
     */
    public NetRequestConfig getNetRequest() {
        return netRequest;
    }


    /**
     * Gets net ui listener.
     *
     * @return the net ui listener
     */
    public NetUIListener getNetUiListener() {
        return netRequest.getNetUIListener();
    }


    /**
     * Gets parameter.
     *
     * @return the parameter
     */
    public NetParameter getParameter() {
        return parameter;
    }

    /**
     * Gets net data parser.
     *
     * @return the net data parser
     */
    public NetDataParser getNetDataParser() {
        return netRequest.getDataParser();
    }

}
