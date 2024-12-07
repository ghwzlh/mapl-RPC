package com.ghw.maplrpc.serialization.jdk;

import com.ghw.maplrpc.exception.SerializerException;
import com.ghw.maplrpc.serialization.api.Serialization;

import java.io.*;

public class JDKSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T object) {
        if (object == null) {
            throw new SerializerException("the object is NULL");
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserializa(byte[] bytes, Class<T> cls) {
        if(bytes == null) {
            throw new SerializerException("the bytes is NULL");
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T)objectInputStream.readObject();
        } catch (Exception e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }
}
