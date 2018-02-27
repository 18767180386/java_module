package com.aiiju.pay.business.wx.common;

import java.util.Random;

/**
 * 
 * @ClassName: RandomStringGenerator 
 * @Description: 随机数工具
 * @author 小飞 
 * @date 2016年12月7日 下午4:03:47 
 *
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
