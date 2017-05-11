package com.changtu.development.net.parser;

import com.changtu.development.net.tools.GsonUtil;

import java.lang.reflect.Type;

/**
 * 网络返回数据，JSON的解析类，通过Google gson实现
 *
 * @version V1.0
 * @date 2016.12.07
 */
public class JsonDataParser implements NetDataParser {

    /**
     * Parse t.
     *
     * @param data     the data
     * @param classOfT the class of t
     * @return the t
     */
    @Override
    public Object parse(Object data, Type classOfT) {
        if (data != null && classOfT != null) {

            if (data.getClass() != classOfT) {
                return GsonUtil.fromJsonStringToCollection(
                        String.valueOf(data), classOfT);
            } else {
                return data;
            }

        }

        return null;

    }
}
