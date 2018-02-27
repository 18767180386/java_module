package com.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCommonUtil {
    public static String endFileDir () {
        Date date = new Date(System. currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd" );
        String str = sdf.format(date).toString();
        StringBuffer sb = new StringBuffer();
        char[] timeArr = str.toCharArray();
        sb = sb.append(timeArr[2]).append(timeArr[3]);
        // str = ""+timeArr[2]+timeArr[3];
        if (timeArr[4] != '0') {
            sb = sb.append(timeArr[4]);
            // str+=timeArr[4];
        }
        sb = sb.append(timeArr[5]).append( "/");
        // str+=""+timeArr[5]+"/";//根据当前系统环境确定分隔符


        //确定天数作为文件夹,测试部不需要天数，直接注释即可
        if(timeArr[6]!= '0'){
            sb = sb.append(timeArr[6]);
        }
        sb = sb.append(timeArr[7]).append( "/");
        return sb.toString().trim();
    }
}
