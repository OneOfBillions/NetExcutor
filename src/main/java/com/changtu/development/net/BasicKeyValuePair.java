package com.changtu.development.net;

/**
 * 基本价值对
 *
 * @version V1.0
 * @date 2016.12.02
 */
public class BasicKeyValuePair implements KeyValuePair {
    /**
     * Key.
     */
    private String key;
    /**
     * Value.
     */
    private String value;

    /**
     * Instantiates a new Basic key value pair.
     *
     * @param key   the key
     * @param value the value
     */
    public BasicKeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Instantiates a new Basic key value pair.
     */
    private BasicKeyValuePair() {

    }

    /**
     * Gets key.
     *
     * @return the key
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}
