package com.aiiju.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 
 * @ClassName: ExceptionUtil 
 * @Description: 异常工具类
 * @author 小飞 
 * @date 2016年11月16日 下午1:22:14 
 *
 */
public class ExceptionUtil {
	
	private ExceptionUtil() {}
	
	/**
	 * 获取异常的堆栈信息
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
