package com.aiiju.pay.business.zfb.util;

import java.lang.reflect.Type;
import java.util.List;

import com.aiiju.pay.business.zfb.hb.EquipStatus;
import com.aiiju.pay.business.zfb.hb.EquipStatusAdapter;
import com.aiiju.pay.business.zfb.hb.ExceptionInfo;
import com.aiiju.pay.business.zfb.hb.ExceptionInfoAdapter;
import com.aiiju.pay.business.zfb.hb.TradeInfo;
import com.aiiju.pay.business.zfb.hb.TradeInfoAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @ClassName: GsonFactory 
 * @Description: 使用google gson作为json序列化反序列化工具
 * @author 小飞 
 * @date 2016年12月3日 上午10:09:42 
 *
 */
public class GsonFactory {

    private static class GsonHolder {
        private static Type exceptionListType = new TypeToken<List<ExceptionInfo>>(){}.getType();
        private static Type tradeInfoListType = new TypeToken<List<TradeInfo>>(){}.getType();

        private static Gson gson = new GsonBuilder()
                                .registerTypeAdapter(exceptionListType, new ExceptionInfoAdapter())
                                .registerTypeAdapter(tradeInfoListType, new TradeInfoAdapter())
                                .registerTypeAdapter(EquipStatus.class, new EquipStatusAdapter())
                                .create();
    }

    public static Gson getGson() {
        return GsonHolder.gson;
    }
}
