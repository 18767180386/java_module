package com.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utils {

    /**
     *
     *
     * @param s
     * @return
     */
    public  static  boolean  isEmpty(String s)
    {
        if(s == null || s.length() <= 0)
        {
            return true;
        }
        return false;
    }



    /**
     * 将参数进行编码码
     */

      public  static String urlEncoder(String json) {
        String encoder = null;
        try {
            encoder = URLEncoder.encode(json, "UTF-8");
            //  String str=URLDecoder.decode(encoder, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder;
    }


    /**
     *
     *
     * @param json
     * @return
     */
    public  static String urlDecoder(String json) {
        String encoder = null;
        try {
             encoder=URLDecoder.decode(json, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder;
    }



}
