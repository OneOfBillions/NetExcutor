package com.changtu.development.net.core;

import android.content.Context;
import android.text.TextUtils;

import com.changtu.development.net.Canceller;
import com.changtu.development.net.KeyValuePair;
import com.changtu.development.net.NetResponseInfo;
import com.changtu.development.net.parameter.NetParameter;
import com.changtu.development.net.tools.PersistentCookieStore;
import com.changtu.development.net.tools.URLEncodedUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 用okhttp网络框架实现{@link NetEngine}
 *
 * @version V1.0
 * @date 2016.12.02
 */
public class OkHttpNetEngine implements NetEngine {


    /**
     * The DEBUG TAG.
     */
    public static final String TAG = OkHttpNetEngine.class.getSimpleName();

    /**
     * Instantiates a new Ok http net engine.
     *
     * @param context the context
     */
    public OkHttpNetEngine(Context context) {
        initContext(context);
    }

    /**
     * Add task tag.
     *
     * @param taskTag the task tag
     */
    @Override
    public void addTaskTag(Object taskTag) {
        this.taskTag = taskTag;
    }

    /**
     * 任务TAG
     */
    private Object taskTag;

    /**
     * @version V1.0
     * @Title: Ok http excutor
     * @date 2016.12.02
     * @Title: Ok http excutor
     */
    private static class OkHttpExcutor {
        /**
         * M ok http client.
         */
        private static final OkHttpClient okHttpClient = new OkHttpClient();
        /**
         * 连接超时.
         */
        private static final int CONNECT_TIME_OUT_MS = 60 * 1000;
        /**
         * 读取超时.
         */
        private static final int READ_TIME_OUT_MS = 60 * 1000;


        static {
            okHttpClient.setConnectTimeout(CONNECT_TIME_OUT_MS,
                    TimeUnit.MILLISECONDS);
            okHttpClient.setWriteTimeout(READ_TIME_OUT_MS, TimeUnit.MILLISECONDS);
            okHttpClient.setReadTimeout(READ_TIME_OUT_MS, TimeUnit.MILLISECONDS);

            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });


            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] x509Certificates = new X509Certificate[0];
                        return x509Certificates;
                    }
                }}, new SecureRandom());
                okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

        }


        /**
         * Init persistent cookie store.
         *
         * @param context the context
         */
        public static void initPersistentCookieStore(Context context) {
            if (okHttpClient.getCookieHandler() == null) {
                if (context != null) {
                    Context application = context.getApplicationContext();
                    if (application != null) {
                        okHttpClient.setCookieHandler(new CookieManager(
                                new PersistentCookieStore(application), CookiePolicy.ACCEPT_ALL));


                    }
                }
            }

        }

        /**
         * Cancel task.
         *
         * @param taskTag the task tag
         */
        public static void cancelTask(Object taskTag) {
            if (taskTag != null) {
                okHttpClient.cancel(taskTag);
            }
        }

        /**
         * Request by post net response info.
         *
         * @param params  the params
         * @param taskTag the task tag
         * @param method  the method
         * @return the net response info
         */
        public static NetResponseInfo requestByMethod(NetParameter params, Object taskTag, String
                method) {
            NetResponseInfo netEventInfo = new NetResponseInfo();//返回信息
            Request.Builder builder = new Request.Builder();
            if (taskTag != null) {
                builder.tag(taskTag);
            }


            ResponseBody responseBody = null;
            try {
                FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
                //请求参数拼装
                List<KeyValuePair> list = params.createParameters();
                Request request;
                if (POST.equals(method)) {
                    if (list != null && !list.isEmpty()) {
                        for (KeyValuePair keyValuePair : list) {
                            formEncodingBuilder.add(keyValuePair.getKey(),
                                    keyValuePair.getValue());

                        }
                    }
                    request = builder.url(params.getRequestURL())
                            .post(formEncodingBuilder.build()).build();
                } else {
                    String url = params.getRequestURL();
                    if (list != null && !list.isEmpty()) {
                        url += "?"
                                + URLEncodedUtils.format(list, "UTF-8");
                    }
                    request = builder.url(url).build();
                }

                Call call = okHttpClient.newCall(request);
                netEventInfo.setStartTime(String.valueOf(System.currentTimeMillis()));//网络耗时统计
                Response response = call.execute();
                netEventInfo.setEndTime(String.valueOf(System.currentTimeMillis()));//网络耗时统计
                netEventInfo.setHttpCode(String.valueOf(response.code()));///网络返回状态码
                if (response.isSuccessful()) {
                    netEventInfo.setSuccess(true);
                    responseBody = response.body();
                    if (responseBody != null) {
                        String reponseStr = responseBody.string();

                        if (!TextUtils.isEmpty(reponseStr)) {
                            //网络返回结果解码
                            netEventInfo.setResult(params.decodeResponse(reponseStr));
                        }
                    }


                }
            } catch (Exception e) {
                netEventInfo.setException(e.toString());//记录网络请求错误
            } finally {
                try {
                    responseBody.close();
                } catch (Exception e) {

                }


            }
            return netEventInfo;


        }


    }


    /**
     * Init context.
     *
     * @param context the context
     */
    @Override
    public void initContext(Context context) {
        if (context != null) {
            OkHttpExcutor.initPersistentCookieStore(context);
        }
    }

    /**
     * Request by post net response info.
     *
     * @param params the params
     * @return the net response info
     */
    @Override
    public NetResponseInfo requestByPost(NetParameter params) {
        return OkHttpExcutor.requestByMethod(params, taskTag, POST);
    }

    /**
     * Request by get net response info.
     *
     * @param params the params
     * @return the net response info
     */
    @Override
    public NetResponseInfo requestByGet(NetParameter params) {
        return OkHttpExcutor.requestByMethod(params, taskTag, GET);
    }

    /**
     * Cancel task.
     *
     * @param canceller the canceller
     */
    @Override
    public void cancelTask(Canceller canceller) {
        if (canceller != null) {
            OkHttpExcutor.cancelTask(canceller.getTaskTag());
        }

    }

    /** Post. */
    private static final String POST = "POST";
    /** Get. */
    private static final String GET = "GET";


}
