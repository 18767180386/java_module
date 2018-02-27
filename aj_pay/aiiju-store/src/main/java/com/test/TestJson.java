package com.test;

import com.aiiju.store.constant.DiscountTypeSwitch;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJson {

	public static void main(String[] args) {
		
		JSONObject json = new JSONObject();
		
		
		JSONObject jsonw = JSONObject.fromObject(DiscountTypeSwitch.DiscountTypeSwitchOpen);
		
		jsonw.put("1", 1);
		jsonw.put("2", 2);
		jsonw.put("3", 3);
		jsonw.put("4", 4);

		System.out.println(jsonw.toString());
		

		json.put("cmd", "add");
		
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		
		json1.put("key11", 11);
		json1.put("key12", "12");
	
		json2.put("key21", 21);
		json2.put("key22", "22");
		json3.put("key31", 31);
		json3.put("key32", "32");
		
		JSONArray jsonarray = new JSONArray();
		
		jsonarray.add(json1);
		jsonarray.add(json2);
		jsonarray.add(json3);
	
		json.put("jsonarray", jsonarray);
		
		
		json.put("shuzi", 1);
		
		
		System.out.println(json.toString());
		
	}
}
