package com.neo.database;

import android.util.Log;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: Neo
 * Date: 13-7-20
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class MD5 {
    public static String GetMD5(String source) {
        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            Log.e("MD5","Can not encode the string '" + source + "' to MD5!", e);
            return null;
        }

        return sb.toString();
    }
}
