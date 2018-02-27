package com.aiiju.store.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName GsonUtil
 * @Description 封装的GSON解析工具类，提供泛型参数
 * @author zong
 * @CreateDate 2017年5月23日 下午5:00:01
 */
public class GsonUtil {

	private static Gson gson = new GsonBuilder()// 不导出实体中没有用@Expose注解的属性
			.enableComplexMapKeySerialization() // 支持Map的key为复杂对象的形式
			.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")// 时间转化为特定格式
			.disableHtmlEscaping()
			//.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)// 会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
			.setPrettyPrinting()
//			.serializeNulls()
			.create();

	public static Gson getGson() {
		return GsonUtil.gson;
	}

	// 将Json数据解析成相应的映射对象
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	// 将Json数组解析成相应的映射对象列表
	public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
		}.getType());
		return result;
	}
	// 将对象转成Json
	public static  <T>String toJson(Class<T> clazz){
		String json = gson.toJson(clazz); 
		return json;
	}
	
	public static void main(String[] args) {
		
		Map map = new HashMap();
		map.put("a", "16794ui705.iok.la:32542/reputation/operateNotifyUrl?store_id=5");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "");
		System.out.println(GsonUtil.gson.toJson(map));;
		
	}
}
