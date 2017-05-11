package com.changtu.development.net.parameter;


import com.changtu.development.net.KeyValuePair;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1 0001.
 *
 * @version V1.0
 * @date 2017.05.11
 */
public interface NetParameter {


    /**
     * Create parameters list.
     *
     * @return the list
     */
    List<KeyValuePair> createParameters();

    /**
     * Gets request url.
     *
     * @return the request url
     */
    String getRequestURL();

    /**
     * Decode response string.
     *
     * @param response the response
     * @return the string
     */
    String decodeResponse(String response);

}
