package com.ghw.maplrpc.serialization.api;

/**
 * @author ghost
 * @version 1.0.0
 * 序列化通用接口
 */
public interface Serialization {
    // 序列化
    <T> byte[] serialize(T object);

    // 反序列化
    <T> T deserializa(byte[] bytes, Class<T> cls);
}