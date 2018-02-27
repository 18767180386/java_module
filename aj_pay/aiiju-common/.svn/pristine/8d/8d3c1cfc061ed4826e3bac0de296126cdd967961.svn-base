package com.aiiju.common.printtemplate;

import java.io.UnsupportedEncodingException;

/**
 * 中英文校验的处理
 * @author a123demi
 *
 */
public class EnChValidate {
    public static void main(String[] args){
         
        String validateStr= "中英文校验abcde接口ii";
         
        int bytesStrLength = getBytesStrLength(validateStr);
        int chineseLength = getChineseLength(validateStr);
        int regexpLength = getRegExpLength(validateStr);
        System.out.println("getBytesLength:" + bytesStrLength + ",chineseLength:" + chineseLength + 
                ",regexpLength:" + regexpLength);
         
    }
     
    /**
     * 根据字符编码 字节数生成一个临时的字符串
     * @param validateStr
     * @return
     */
    public static int getBytesStrLength(String validateStr){
        String tempStr = "";
        try {
            tempStr = new String(validateStr.getBytes("gb2312"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tempStr.length();
    }
     
    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param validateStr
     *            指定的字符串
     * @return 字符串的长度
     */
    public static int getChineseLength(String validateStr) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < validateStr.length(); i++) {
            /* 获取一个字符 */
            String temp = validateStr.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
     
    /**
     * 利用正则表达式将每个中文字符转换为"**"
     * 匹配中文字符的正则表达式： [\u4e00-\u9fa5]
     * 匹配双字节字符(包括汉字在内)：[^\x00-\xff]
     * @param validateStr
     * @return
     */
    public static int getRegExpLength(String validateStr){
//      String temp = validateStr.replaceAll("[\u4e00-\u9fa5]", "**");
        String temp = validateStr.replaceAll("[^\\x00-\\xff]", "**");
        return temp.length();
    }
}