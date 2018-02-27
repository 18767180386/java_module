package com.aiiju.common.exception;
/**
 * 
 * @ClassName: CustomException 
 * @Description: 自定义异常
 * @author 小飞 
 * @date 2016年11月9日 下午1:26:49 
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6416041568583841311L;
	
	public CustomException(String message) {
		super(message); 
	}
	
}
