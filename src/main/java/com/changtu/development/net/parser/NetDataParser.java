package com.changtu.development.net.parser;

import java.lang.reflect.Type;

/**
 * 网络返回数据解析类，具体可以实现 XML, JSON等格式的转换
 *
 * @version V1.0
 *  2016.12.07
 */
public interface NetDataParser {

    /**
     * Parse t.
     *
     * @param data     the data
     * @param classOfT the class of t
     * @return the t
     */
    Object parse(Object data, Type classOfT);
}
