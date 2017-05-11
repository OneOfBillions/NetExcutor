package com.changtu.development.net;

/**
 * Created by Administrator on 2016/12/2 0002.
 *
 * @version V1.0
 * @date 2017.05.11
 */
public class NetException extends RuntimeException {

    /**
     * Constructs a new {@code CttripNetException} that includes the current
     * stack trace.
     */
    public NetException() {
    }

    /**
     * Constructs a new {@code CttripNetException} with the current stack
     * trace and the specified detail message.
     *
     * @param detailMessage the detail message for this exception.
     */
    public NetException(String detailMessage) {
        super(detailMessage);
    }
}
