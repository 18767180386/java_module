package com.aiiju.pay.constant;

/**
 * 
 * @ClassName: Constants 
 * @Description: 支付结果常量
 * @author 小飞 
 * @date 2016年12月2日 下午5:33:39 
 *
 */
public class ResultConstant {

	private ResultConstant() {
    }

    public static final String SUCCESS = "10000"; // 成功
    public static final String PAYING = "10003";  // 用户支付中
    public static final String FAILED = "40004";  // 失败
    public static final String ERROR = "20000"; // 系统异常
}
