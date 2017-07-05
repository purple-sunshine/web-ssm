package com.heitian.ssm.arch.utils;

import java.io.*;

/**
 * 将可序列化的对象进行序列化和反序列化
 * @author songjunjie
 */
public class SerializableUtils {

    public static String serialize(Serializable obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return Encodes.encodeBase64(bos.toByteArray());
//            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Object deserialize(String serializedStr) {
        try {
            byte[] bytes = Encodes.decodeBase64(serializedStr);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}
