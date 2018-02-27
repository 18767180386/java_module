/**   
 * @Title PropertiesUtil.java 
 * @Package com.ajy.util 
 * @Description TODO(用一句话描述该文件做什么) 
 * @author 哪吒  
 * @date 2016年8月31日 下午12:06:20 
 * @version V1.0   
 */
package com.aiiju.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @ClassName: PropertiesUtil
 * @Description: 读取配置文件工具类
 * @author qiyu
 * @date 2016年8月31日 下午12:06:20
 * 
 */
public class PropertiesUtil {

    /**
     * 
     * @Title: outputvalusofProperty 
     * @Description: 输出name属性文件内容
     * @param name
     * @throws IOException 
     * @throws
     */
    public static void outputvalusofProperty(String name) throws IOException{
        InputStream in = PropertiesUtil.class.getResourceAsStream(name);
        Properties pro = new Properties();
        pro.load(in);

        for (String key:pro.stringPropertyNames()){
            System.out.println("key:"+key+" "+"value:"+pro.getProperty(key));
        }
    }



    
    /**
     * 
     * @Title: getPropertyByKey 
     * @Description: 根据key获取值
     * @param key
     * @return
     * @throws IOException 
     * @throws
     */
    public static String getPropertyByKey(String key) {
        InputStream in = PropertiesUtil.class.getResourceAsStream("/apiInfo.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(pro.containsKey(key))
            return pro.getProperty(key);
        else 
            return null;
    }
    
    /**
     * 
     * @Title: getPropertyByKey 
     * @Description: 根据key和fileName（需要包含后缀）获取值 
     * @param 
     * @return
     * @throws IOException 
     * @throws
     */
    public static String getPropertyByKey(String key,String fileName) {
        InputStream in = PropertiesUtil.class.getResourceAsStream("/"+fileName);
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(pro.containsKey(key))
            return pro.getProperty(key);
        else 
            return null;
    }
    
}
