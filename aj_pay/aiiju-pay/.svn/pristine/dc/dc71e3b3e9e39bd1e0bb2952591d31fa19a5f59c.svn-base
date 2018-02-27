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
 * @ClassName: TradeInfoAdapter 
 * @Description: 交易信息适配器
 * @author 小飞 
 * @date 2016年12月3日 上午10:11:21 
 *
 */
public class TradeInfoAdapter implements JsonSerializer<List<TradeInfo>> {
    @Override
    public JsonElement serialize(List<TradeInfo> tradeInfoList, Type type, JsonSerializationContext jsonSerializationContext) {
        if (Utils.isListEmpty(tradeInfoList)) {
            return null;
        }

        TradeInfo tradeInfo = tradeInfoList.get(0);
        if (tradeInfo instanceof PosTradeInfo) {
            return new JsonPrimitive(StringUtils.join(tradeInfoList, ""));
        }

        return jsonSerializationContext.serialize(tradeInfoList);
    }
}
