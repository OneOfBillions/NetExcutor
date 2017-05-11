package com.changtu.development.net.tools;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * GSON解析工具类.
 *
 * @author 牛翔
 * @version 1.0
 * @date 2015 -4-23
 */
public class GsonUtil {
    /** The Constant TAG. */
    public static final String TAG = GsonUtil.class.getSimpleName();

    /** The Constant GSON. */
    private static final Gson GSON = new Gson();

    /**
     * Instantiates a new gson util.
     */
    private GsonUtil() {

    }

    /**
     * Gets the single instance of GsonUtil.
     *
     * @return single instance of GsonUtil
     */
    private static synchronized Gson getInstance() {
        return GSON;
    }

    /**
     * From object to json string.
     *
     * @param object the object
     * @return the string
     */
    public static String fromObjectToJsonString(Object object) {
        String result = null;
        if (object != null) {
            result = getInstance().toJson(object);
        }
        return result;

    }

    /**
     * From json string to obejct.
     *
     * @param <T>        the generic type
     * @param jsonString the json string
     * @param classOfT   the class of t
     * @return the t
     */
    public static <T> T fromJsonStringToObejct(String jsonString,
                                               Class<T> classOfT) {
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                return getInstance().fromJson(jsonString, classOfT);

            } catch (Exception e) {
                Log.d(TAG, e.toString());

            }

        }
        return null;

    }

    /**
     * From json string to collection.
     *
     * @param <T>        the generic type
     * @param jsonString the json string
     * @param typeOfT    the type of t
     * @return the t
     */
    public static <T> T fromJsonStringToCollection(String jsonString,
                                                   Type typeOfT) {
        if (!TextUtils.isEmpty(jsonString)) {

            try {
                return getInstance().fromJson(jsonString, typeOfT);

            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }

        }
        return null;

    }

}
