package com.changtu.development.net;


import static com.changtu.development.net.NetExcutor.RunMode.CURRENT_THREAD;
import static com.changtu.development.net.NetExcutor.RunMode.MUTIPLE_THREAD_POOL;
import static com.changtu.development.net.NetExcutor.RunMode.SINGLE_THREAD_POOL;

/**
 * 网络请求方式配置
 *
 * @version V1.0
 *  2016.07.27
 */
public class NetOptions {

    /**
     * 无缓存，运行在多线程池中，UI线程中回调
     */
    public final static NetOptions DEFAULT = new NetOptions();

    /**
     * 缓存，运行在多线程池中，UI线程中回调
     */
    public final static NetOptions APPLAY_CACHE = new NetOptions(MUTIPLE_THREAD_POOL, true, true);
    /**
     * 无缓存，运行在单线程池中，UI线程中回调
     */
    public final static NetOptions SINGLE_THREAD = new NetOptions(SINGLE_THREAD_POOL, false, true);
    /**
     * 无缓存，运行在单线程池中，不在UI回调
     */
    public final static NetOptions CURRENT_THREAD_RUN = new NetOptions(CURRENT_THREAD, false, false);
    /**
     * 运行模式
     */
    private NetExcutor.RunMode runMode = MUTIPLE_THREAD_POOL;
    /**
     * 网络请求是否应用缓存（默认是运行在多次那成线程池中。）
     */
    private boolean isApplyCache = false;
    /**
     * 网络请求的结果返回是否需要在UI线程中执行
     */
    private boolean isPostUIThread = true;

    /**
     * Instantiates a new Net options.
     *
     * @param runMode        the run mode
     * @param isApplyCache   the is apply cache
     * @param isPostUIThread the is post ui thread
     */
    public NetOptions(NetExcutor.RunMode runMode, boolean isApplyCache, boolean isPostUIThread) {
        this.runMode = runMode;
        this.isApplyCache = isApplyCache;
        this.isPostUIThread = isPostUIThread;
    }

    /**
     * Instantiates a new Options.
     */
    public NetOptions() {
    }

    /**
     * Sets run mode.
     *
     * @param runMode the run mode
     */
    public void setRunMode(NetExcutor.RunMode runMode) {
        this.runMode = runMode;
    }

    /**
     * Sets apply cache.
     *
     * @param applyCache the apply cache
     */
    public void setApplyCache(boolean applyCache) {
        isApplyCache = applyCache;
    }

    /**
     * Sets post ui thread.
     *
     * @param postUIThread the post ui thread
     */
    public void setPostUIThread(boolean postUIThread) {
        isPostUIThread = postUIThread;
    }

    /**
     * Gets run mode.
     *
     * @return the run mode
     */
    public NetExcutor.RunMode getRunMode() {
        return runMode;
    }

    /**
     * Is apply cache boolean.
     *
     * @return the boolean
     */
    public boolean isApplyCache() {
        return isApplyCache;
    }

    /**
     * Is post ui thread boolean.
     *
     * @return the boolean
     */
    public boolean isPostUIThread() {
        return isPostUIThread;
    }


}
