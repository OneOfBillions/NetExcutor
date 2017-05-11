package com.changtu.development.net.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/**
 * Created by <a href="http://www.jiechic.com" target="_blank">jiechic</a> on
 * 15/5/27.
 *
 * @version V1.0
 * @date 2017.05.11
 */
public class SerializableHttpCookie implements Serializable {
    /** Serial version uid. */
    private static final long serialVersionUID = 6374381323722046732L;

    /** Cookie. */
    private transient final HttpCookie cookie;
    /** Client cookie. */
    private transient HttpCookie clientCookie;

    /**
     * Instantiates a new Serializable http cookie.
     *
     * @param cookie the cookie
     */
    public SerializableHttpCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    /**
     * Gets cookie.
     *
     * @return the cookie
     */
    public HttpCookie getCookie() {
        HttpCookie bestCookie = cookie;
        if (clientCookie != null) {
            bestCookie = clientCookie;
        }
        return bestCookie;
    }

    /**
     * Write object.
     *
     * @param out the out
     * @throws IOException the io exception
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());
        out.writeObject(cookie.getComment());
        out.writeObject(cookie.getCommentURL());
        out.writeObject(cookie.getDomain());
        out.writeLong(cookie.getMaxAge());
        out.writeObject(cookie.getPath());
        out.writeObject(cookie.getPortlist());
        out.writeInt(cookie.getVersion());
        out.writeBoolean(cookie.getSecure());
        out.writeBoolean(cookie.getDiscard());
    }

    /**
     * Read object.
     *
     * @param in the in
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        clientCookie = new HttpCookie(name, value);
        clientCookie.setComment((String) in.readObject());
        clientCookie.setCommentURL((String) in.readObject());
        clientCookie.setDomain((String) in.readObject());
        clientCookie.setMaxAge(in.readLong());
        clientCookie.setPath((String) in.readObject());
        clientCookie.setPortlist((String) in.readObject());
        clientCookie.setVersion(in.readInt());
        clientCookie.setSecure(in.readBoolean());
        clientCookie.setDiscard(in.readBoolean());
    }
}