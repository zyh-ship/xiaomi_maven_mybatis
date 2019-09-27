package com.qf.utils;

import java.util.UUID;

/**
 * @author zyh
 * @date 2019/9/10 0010 16:22
 * 佛祖保佑
 */
public class RandomUtils {
    public static String CreateUUId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
