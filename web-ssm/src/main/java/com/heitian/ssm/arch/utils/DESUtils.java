package com.heitian.ssm.arch.utils;/*
package com.unionx.core.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Scanner;

*/
/**
 * 对策加密工具类
 * Created by songjunjie on 2015/8/19.
 *//*

public class DESUtils {
    private static Key key;
    */
/**
     * 秘钥字符串
      *//*

    private static final String KEY_STRING = "AKlMU89D3FchIkhKyMma6FiE";
    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(KEY_STRING.getBytes()));
            key = generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    */
/**
     * 对字符串进行对称加密
     * @param value 要加密的字符串
     * @return
     *//*

    public static String encrypt(String value) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = value.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    */
/**
     * 对加密的字符串进行解密
     * @param value 加密过的字符串
     * @return
     *//*

    public static String decrypt(String value) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] strBtyes = base64Decoder.decodeBuffer(value);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypStrBytes = cipher.doFinal(strBtyes);
            return new String(decrypStrBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要加密的字符串");
            String value = scanner.next();
            System.out.println(encrypt(value));
        }
    }
}
*/
