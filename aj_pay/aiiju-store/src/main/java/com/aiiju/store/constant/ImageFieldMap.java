package com.aiiju.store.constant;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName ImageFieldMap
 * @Description
 * @author zong
 * @CreateDate 2017年6月1日 上午10:08:29
 */
public class ImageFieldMap {
	
	private static Map<String,String> map = new HashMap<String,String>();
	
	static{
		map.put("brand_logo", "品牌logo");
		map.put("main_image", "门店首图 ");
		map.put("audit_images", "门头照内景照");
		map.put("licence", " 门店营业执照图片");
		map.put("business_certificate", "许可证");
		map.put("auth_letter", "门店授权函");
		map.put("other_authorization", "其他资质");
		
		map.put("bossPositive", "法人身份证正面【私密区】");
		map.put("bossBack", "法人身份证背面【私密区】");
		map.put("blPic", "营业执照图片【私密区】");
		map.put("pic1", "整体门面（含招牌）图片【公共区】");
		map.put("pic2", "收银台图片【公共区】");
		map.put("pic3", "店内环境图片【公共区】");
		map.put("licencePic", "许可证图片【私密区】");
		map.put("licencePic2", "授权文件【私密区】");

		
		map.put("contractPic1", "合同照片【私密区】");
		map.put("contractPic2", "合同照片补充【私密区】");

	}
	
	public static String getFieldRemarkByName(String field_name){
		
		return ImageFieldMap.map.get(field_name);
		
	}

}
