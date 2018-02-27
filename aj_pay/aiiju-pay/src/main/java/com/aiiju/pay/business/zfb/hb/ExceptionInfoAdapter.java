package com.aiiju.pay.business.zfb.hb;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.aiiju.pay.business.zfb.util.Utils;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @ClassName: ExceptionInfoAdapter 
 * @Description: 异常信息适配器
 * @author 小飞 
 * @date 2016年12月3日 上午10:06:44 
 *
 */
public class ExceptionInfoAdapter implements JsonSerializer<List<ExceptionInfo>> {
    @Override
    public JsonElement serialize(List<ExceptionInfo> exceptionInfos, Type type, JsonSerializationContext jsonSerializationContext) {
        if (Utils.isListEmpty(exceptionInfos)) {
            return null;
        }

        return new JsonPrimitive(StringUtils.join(exceptionInfos, "|"));
    }
}
