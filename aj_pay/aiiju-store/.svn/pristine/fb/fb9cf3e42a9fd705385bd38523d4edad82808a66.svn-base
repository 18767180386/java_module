package com.aiiju.store.editor;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
/**
 * 
* @ClassName: DateEditor 
* @Description: 
* @author 小飞 
* @date 2017年2月22日 下午3:16:41
 */
public class DateEditor extends PropertyEditorSupport {

    private String pattern;
    
    public DateEditor(String pattern) {
        this.pattern = pattern;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        DateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        if (!StringUtils.isBlank(text)) {
            try {
                date = format.parse(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setValue(date);
    }
}
