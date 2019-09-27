package com.qf.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zyh
 * @date 2019/9/10 0010 16:28
 * 佛祖保佑
 */
public class MD5Utils {
    public static String md5(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("utf-8"));
            byte[] digest = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            String s = bigInteger.toString(16);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void tt1(){
        System.out.println(new MD5Utils().md5("zaaaaaaa"));
    }
}
