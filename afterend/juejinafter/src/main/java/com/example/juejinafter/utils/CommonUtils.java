package com.example.juejinafter.utils;

import java.security.MessageDigest;

/**
 * 工具类
 */
public class CommonUtils {

    /**
     * MD5加密类
     */
    public static String MD5(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
