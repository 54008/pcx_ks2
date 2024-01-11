package com.pcx.protocol;

import java.io.*;

public interface Serializer {
    <T> T deserialize(Class<T> clazz,byte[]bytes);
    <T> byte[] serialize(T Object);
    enum Algorithm implements Serializer{
        Java{
            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
                    return (T)objectInputStream.readObject();
                }catch (IOException |ClassNotFoundException e){
                    throw  new RuntimeException("deserializer failure",e);
                }
            }

            @Override
            public <T> byte[] serialize(T Object) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
                    oos.writeObject(Object);
                    return byteArrayOutputStream.toByteArray();
                }catch (IOException  e){
                    throw  new RuntimeException("deserializer failure",e);
                }
            }
        },
    }
}
