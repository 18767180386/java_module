package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.pojo.Shop;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.ShopService;
/**
 * 
 * @ClassName: ShopController 
 * @Description: 店铺控制器
 * @author 小飞 
 * @date 2016年11月17日 上午9:43:40 
 *
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

	private static Logger logger = Logger.getLogger(ShopController.class);
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(Shop shop,String name) {
		try {
			return this.shopService.save(shop, name);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/saveShop")
	@ResponseBody
	public Result saveShop(Shop shop) {
		try {
			return this.shopService.saveShop(shop);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	
	@RequestMapping("/shopList")
	@ResponseBody
	public Result getShopList(String role,String storeId) {
		try {
			return this.shopService.getShopList(role,storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	
	@RequestMapping("/switchList")
	@ResponseBody
	public Result switchList(String role,String storeId) {
		try {
			return this.shopService.switchList(role,storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/queryShopList")
	@ResponseBody
	public Result queryShopList(String role,String storeId) {
		try {
			return this.shopService.queryShopList(role,storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(Shop shop) {
		try {
			return this.shopService.update(shop);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR+";"+e.getMessage());
		}
	}
	
	@RequestMapping("/updateShopGoodsAuth")
	@ResponseBody
	public Result updateShopGoodsAuth(Shop shop) {
		try {
			return this.shopService.updateShopGoodsAuth(shop);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR+";"+e.getMessage());
		}
	}
	
	
	@RequestMapping("/get")
	@ResponseBody
	public Result getShop(Integer id) {
		try {
			return this.shopService.getById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	
	
	@RequestMapping("/getSwitchShop")
	@ResponseBody
	public Result getSwitchShop(Integer id) {
		try {
			return this.shopService.getSwitchShop(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id) {
		try {
			return this.shopService.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Result upload(@RequestParam MultipartFile uploadFile,String storeId) {
		try {
			return this.shopService.upload(uploadFile, storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	/**
	 * 开启同步功能
	 * @param storeId 主店铺的id
	 * @param status  0表示关闭同步，1表示开启同步(新创建一个店铺默认值是1)
	 * @return
	 */
	@RequestMapping("/openSynchronize")
	public String openSynchronize(String storeId,String status) {
		try {
			return this.shopService.openSynchronize(storeId, status);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//return Result.build(500,WebConstant.SYS_ERROR);
			return JsonUtils.objectToJson(Result.build(500,WebConstant.SYS_ERROR));
		}
	}
	
}
