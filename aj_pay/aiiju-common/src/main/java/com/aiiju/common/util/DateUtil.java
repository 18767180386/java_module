package com.aiiju.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期工具类
 * @author 小飞
 * @date 2016年12月6日 下午1:45:00
 *
 */
public class DateUtil {
	
	public final static String   DEFAULT_FORMAT ="yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }
    
    
    /**
     * 获取当前时间，默认yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String currentTime( String pattern) {
    	if(pattern==null){
    		pattern=DEFAULT_FORMAT;
    	}
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }
    

    /**
     * Date转字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
    
    
    public static Date parseStr(String dateStr, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(dateStr);
    }

    /**
     * 
     * @param n 正数：今天之后n天 负数：今天之前n天
     * @return
     */
    public static String getAnyDate(int n) {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + n);
        return formatDate(c.getTime(), "yyyy-MM-dd");
    }

    
    public static void main(String[] args) throws ParseException {
	//	System.out.println(getThisMonthStartDay());
    	
    	System.out.println(parseStr("2017-09-04 11:43:56","yyyy-MM-dd HH:mm:ss").getTime());
    	System.out.println(parseStr("2017-09-09 11:43:56","yyyy-MM-dd HH:mm:ss").getTime());
    	
    	
    
    	System.out.println(getAnyDate(-6));
    	System.out.println(getAnyDate(0));
    	
    	
    	
    	
    	
    	Map map = getThisWeekStartAndEnd();
    	Map map1 = getThisMonthStartAndEndDay();
    	Map map2 = getLastMonthStartAndEnd();
    	Map map3 = getLastWeekStartAndEnd();
    	
    	
    	System.out.println(map.get("startTime"));
    	System.out.println(map.get("endTime"));
    	System.out.println(map1.get("startTime"));
    	System.out.println(map1.get("endTime"));
    	System.out.println(map2.get("startTime"));
    	System.out.println(map2.get("endTime"));
    	System.out.println(map3.get("startTime"));
    	System.out.println(map3.get("endTime"));
    	
	}
    
    
    
    public static String getFirstDayOnMonth() {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        return formatDate(c.getTime(), "yyyy-MM-dd");
    }
    
     //获取本周时间段
      public static Map<String, String> getThisWeekStartAndEnd() {
    	Map<String, String> map = new HashMap<String, String>();
      Calendar currentDate = new GregorianCalendar();   
      currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
      
      currentDate.set(Calendar.HOUR_OF_DAY, 0);  
      currentDate.set(Calendar.MINUTE, 0);  
      currentDate.set(Calendar.SECOND, 0);  
      currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
      
      map.put("startTime", formatDate(currentDate.getTime(), "yyyy-MM-dd"));
  	  map.put("endTime", getAnyDate(0));
      
      return map;
      }
     
      /**
      * 上周
      * 
      * @return
      */
      public static Map<String, String> getLastWeekStartAndEnd() {
    	  Map<String, String> map = new HashMap<String, String>();
      Calendar cal = Calendar.getInstance();
     

      cal.set(Calendar.DAY_OF_WEEK, 1);
      cal.set(Calendar.HOUR_OF_DAY, 23);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.SECOND, 59);
      cal.set(Calendar.MILLISECOND, 999);
      map.put("endTime", formatDate(cal.getTime(), "yyyy-MM-dd"));
    
      cal.add(Calendar.WEEK_OF_MONTH, -1);
      cal.set(Calendar.DAY_OF_WEEK, 2);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      map.put("startTime", formatDate(cal.getTime(), "yyyy-MM-dd"));

      return map;
      }
      
      
      // 获得本月时间段
      public static Map<String, String> getThisMonthStartAndEndDay() {  
    	  
    	  Map<String, String> map = new HashMap<String, String>();
          Calendar cal = Calendar.getInstance();  
          cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
          cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
      	  map.put("startTime", formatDate(cal.getTime(), "yyyy-MM-dd"));
      	  map.put("endTime", getAnyDate(0));
          return map;
      } 
      
      
  	/**
  	* 上月时间段
  	* @return
  	*/
  	public static Map<String, String> getLastMonthStartAndEnd() {
  
  	Map<String, String> map = new HashMap<String, String>();


  	// 获取Calendar
  	Calendar calendar = Calendar.getInstance();

  	calendar.add(Calendar.MONTH, -1);
  	calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
  	calendar.set(Calendar.HOUR_OF_DAY, 0);
  	calendar.set(Calendar.MINUTE, 0);
  	calendar.set(Calendar.SECOND, 0);
  	calendar.set(Calendar.MILLISECOND, 0);
  	
  	map.put("startTime", formatDate(calendar.getTime(), "yyyy-MM-dd"));
  	

  	Calendar cale = Calendar.getInstance();
  	cale.set(Calendar.DAY_OF_MONTH, 0);
  	cale.set(Calendar.HOUR_OF_DAY, 23);
  	cale.set(Calendar.MINUTE, 59);
  	cale.set(Calendar.SECOND, 59);
  	cale.set(Calendar.MILLISECOND, 999);
 
  	map.put("endTime", formatDate(cale.getTime(), "yyyy-MM-dd"));
  	return map;
  	}
  	
  	 /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() - minute * 60 * 1000);
    }
    
    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
    /**
     * 将指定字符串日期 改为指定格式
     *
     * @param date   字符串日期
     * @param oldPattern      旧格式
     * @param newPattern	新格式
     * @return
     */
    public static String  format(String date,String oldPattern, String newPattern)   {
    	
    	SimpleDateFormat df = new SimpleDateFormat(oldPattern);
    	Date parse;
		try {
			parse = df.parse(date);
		} catch (ParseException e) {
			parse =new Date();
		}
        df.applyPattern(newPattern);
        return df.format(parse);
    }

      
}
