package com.ghw.maplrpc.exception;

/**
 * @author ghost
 * @version 1.0.0
 * 序列化异常类
 */
public class SerializerException extends RuntimeException{

    private static final long serialVersionUID = 9099864246549151198L;

    public SerializerException(final Throwable e) {
        super(e);
    }
    public SerializerException(final String message) {
        super(message);
    }
    public SerializerException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}