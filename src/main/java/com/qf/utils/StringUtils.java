package com.qf.utils;

/**
 * @author zyh
 * @date 2019/9/10 0010 16:16
 * 佛祖保佑
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if (str==null || str.trim().length()==0){
            return true;
        }
        return false;
    }
}
