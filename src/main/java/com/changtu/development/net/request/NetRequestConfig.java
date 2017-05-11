package com.changtu.development.net.request;

import android.content.Context;

import com.changtu.development.net.Canceller;
import com.changtu.development.net.NetOptions;
import com.changtu.development.net.core.NetEngine;
import com.changtu.development.net.listener.NetUIListener;
import com.changtu.development.net.parser.JsonDataParser;
import com.changtu.development.net.parser.NetDataParser;


/**
 * 网络请求的配置类.
 *
 *
 * @version V1.0
 * @date 2016.12.07
 */
public class NetRequestConfig {
    /**
     * @version V1.0
     * @Title: Method
     * @date 2016.12.07
     */
    public enum Method {
        /**
         * Post.
         */
        POST, /**
         * Get.
         */
        GET
    }

    /**
     * 回调的ANDROID CONTEXT.
     */
    private Context context;
    /**
     * 网络引擎.
     */
    private NetEngine netEngine;
    /**
     * 网络配置.
     */
    private NetOptions options;
    /**
     * 回调监听.
     */
    private NetUIListener netUIListener;
    /**
     * 取消网络请求.
     */
    private Canceller canceller;
    /**
     * 网络请求方法.
     */
    private Method method;
    /**
     * 返回数据解析器.
     */
    private NetDataParser dataParser;

    /**
     *
     * 网络请求配置的构造器
     * @version V1.0
     * @Title: Builder
     * @date 2016.12.07
     */
    public static class Builder {
        /**
         * Context.
         */
        private Context context;
        /**
         * Net engine.
         */
        private NetEngine netEngine;
        /**
         * Canceller.
         */
        private Canceller canceller;
        /**
         * Options.
         */
        private NetOptions options;
        /**
         * Net ui listener.
         */
        private NetUIListener netUIListener;
        /**
         * Method.
         */
        private Method method = Method.POST;
        /**
         * Data parser.
         */
        private NetDataParser dataParser = new JsonDataParser();

        /**
         * Sets method.
         *
         * @param method the method
         * @return the method
         */
        public Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        /**
         * Sets context.
         *
         * @param context the context
         * @return the context
         */
        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }


        /**
         * Sets net engine.
         *
         * @param netEngine the net engine
         * @return the net engine
         */
        public Builder setNetEngine(NetEngine netEngine) {
            this.netEngine = netEngine;
            return this;
        }

        /**
         * Sets options.
         *
         * @param options the options
         * @return the options
         */
        public Builder setOptions(NetOptions options) {
            this.options = options;
            return this;
        }

        /**
         * Sets net ui listener.
         *
         * @param netUIListener the net ui listener
         * @return the net ui listener
         */
        public Builder setNetUIListener(NetUIListener netUIListener) {
            this.netUIListener = netUIListener;
            return this;
        }

        /**
         * Sets data parser.
         *
         * @param dataParser the data parser
         * @return the data parser
         */
        public Builder setDataParser(NetDataParser dataParser) {
            this.dataParser = dataParser;
            return this;
        }

        /**
         * Sets canceller.
         *
         * @param canceller the canceller
         * @return the canceller
         */
        public Builder setCanceller(Canceller canceller) {
            this.canceller = canceller;
            return this;
        }

        /**
         * Build view event.
         *
         * @return the view event
         */
        public NetRequestConfig build() {

            return new NetRequestConfig(this);

        }


    }

    /**
     * Instantiates a new View event.
     *
     * @param builder the builder
     */
    protected NetRequestConfig(Builder builder) {

        this.context = builder.context;
        this.options = builder.options;
        this.netUIListener = builder.netUIListener;
        this.canceller = builder.canceller;
        this.netEngine = builder.netEngine;
        this.method = builder.method;
        this.dataParser = builder.dataParser;


    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Gets data parser.
     *
     * @return the data parser
     */
    public NetDataParser getDataParser() {
        return dataParser;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets net engine.
     *
     * @return the net engine
     */
    public NetEngine getNetEngine() {
        return netEngine;
    }


    /**
     * Gets options.
     *
     * @return the options
     */
    public NetOptions getOptions() {
        return options;
    }


    /**
     * Gets net ui listener.
     *
     * @return the net ui listener
     */
    public NetUIListener getNetUIListener() {
        return netUIListener;
    }

    /**
     * Gets canceller.
     *
     * @return the canceller
     */
    public Canceller getCanceller() {
        return canceller;
    }

}
