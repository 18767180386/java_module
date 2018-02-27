package com.aiiju.pay.business.zfb.hb;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * 
 * @ClassName: EquipStatusAdapter 
 * @Description: 设备状态适配器
 * @author 小飞 
 * @date 2016年12月3日 上午10:06:05 
 *
 */
public class EquipStatusAdapter implements JsonSerializer<EquipStatus> {
    @Override
    public JsonElement serialize(EquipStatus equipStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(equipStatus.getValue());
    }
}
