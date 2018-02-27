package com.aiiju.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：前端提交的数据校验
 * 
 * 提交的参数：
 * 
 * 创建人：叶方一
 * 
 * 创建时间：2016年1月29日 下午12:04:13
 * 
 * 修改人：叶方一
 * 
 * 修改时间：2016年1月29日 下午12:04:13
 * 
 * 修改备注：
 * 
 * @version
 * 
 */ 

public class VerifyUtils {
	
	public static String verifyString(String str) throws Exception{
		if( null == str || "".equals(str) || "".equals(str.trim()) ){
			throw new Exception( "参数传递错误");
		}
		return str;
	}

	/**
	 * 手机号验证
	 *
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][2,3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

}
