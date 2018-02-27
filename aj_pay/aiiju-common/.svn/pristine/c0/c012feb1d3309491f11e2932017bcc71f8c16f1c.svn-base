package com.aiiju.common.util;

/**
 * 
 * @ClassName: SerialNumUtil
 * @Description: 随机数工具类
 * @author 小飞
 * @date 2016年12月2日 上午10:40:16
 *
 */
public class RandomUtil {

    private RandomUtil() {
    }

    /**
     * 生成流水号
     * 
     * @return
     */
    public static String generateCode() {
        return "XS" + System.currentTimeMillis() + ((int) ((Math.random() * 9 + 1) * 1000000));
    }

    /**
     * 退款单号
     * 
     * @return
     */
    public static String generateRefundCode() {
        return "TK" + System.currentTimeMillis() + ((int) ((Math.random() * 9 + 1) * 1000000));
    }

    /**
     * 5位数验证码
     * 
     * @return
     */
    public static String generateMcardCode() {
        return ((int) ((Math.random() * 9 + 1) * 10000)) + "";
    }

    /**
     * 6位数验证码
     * 
     * @return
     */
    public static String generateVerificationCode() {
        return ((int) ((Math.random() * 9 + 1) * 100000)) + "";
    }

    /**
     * 8位数验证码
     * 
     * @return
     */
    public static String generateProductId() {
        return ((int) ((Math.random() * 9 + 1) * 10000000)) + "";
    }

    /**
     * 18位数隨機數
     * 
     * @return
     */
    public static String getPrintSerial() {
        return System.currentTimeMillis()+""+((int) ((Math.random() * 9 + 1) * 10000));
    }
    
    
    
     public static void main(String arg[]){
    	System.out.println(System.currentTimeMillis()); 
    	
 
     }
}
