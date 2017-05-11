package com.changtu.development.net.parameter;

import com.changtu.development.net.KeyValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的网络参数类.
 *
 * @version V1.0
 *  2016.12.06
 */
public class CommonNetParameter implements NetParameter {

    /**
     * Key value pairs.
     */
    private List<KeyValuePair> keyValuePairs = new ArrayList<>();
    /**
     * Url.
     */
    private String url;

    /**
     * Instantiates a new Common parameter.
     *
     * @param url           the url
     * @param keyValuePairs the key value pairs
     */
    public CommonNetParameter(String url, List<KeyValuePair> keyValuePairs) {
        this.keyValuePairs = keyValuePairs;
        this.url = url;
    }

    /**
     * Instantiates a new Common parameter.
     */
    private CommonNetParameter() {
    }

    /**
     * Create parameters list.
     *
     * @return the list
     */
    @Override
    public List<KeyValuePair> createParameters() {
        return keyValuePairs;
    }

    /**
     * Gets request url.
     *
     * @return the request url
     */
    @Override
    public String getRequestURL() {
        return url;
    }

    /**
     * Decode response string.
     *
     * @param response the response
     * @return the string
     */
    @Override
    public String decodeResponse(String response) {
        try {
            return URLDecoder.decode(response, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
