package com.aiiju.store.controller.rest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Goods;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.InventoryService;

/** 
 * @ClassName InventoryController
 * @Description 库存管理
 * @author zong
 * @CreateDate 2017年6月30日 下午4:06:25
 */

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	private static Logger logger = Logger.getLogger(InventoryController.class);
	
	
	@Autowired
	private InventoryService inventoryService;
	
    @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd HH:mm:ss"));
    }
	
    @RequestMapping("/list")
    @ResponseBody
    public Result inventoryList(String storeId) {
    	
    	long startTime = System.currentTimeMillis();
    	
    	// keyword 
    	Result result = inventoryService.getInventoryList(storeId);
    	//Result result = inventoryService.getInventoryListByGoodsTypeId("839");
    	
    	long endTime = System.currentTimeMillis();
    	
    	System.out.println("耗时："+(endTime-startTime)+"ms");
    	
    	return result;
    }
    
    
    @RequestMapping("/getInventoryListByGoodsType")
    @ResponseBody
    public Result getInventoryListByGoodsType(Long goodsTypeId,@RequestParam(value = "currentNum", defaultValue = "1") Integer currentNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
    	
    	long startTime = System.currentTimeMillis();
    	
    	// keyword 
    	Result	result =  this.inventoryService.getInventoryListByGoodsTypeId(goodsTypeId,currentNum,pageSize);
	
    	
    	
    	long endTime = System.currentTimeMillis();
    	
    	System.out.println("耗时："+(endTime-startTime)+"ms");
    	
    	return result;
    }
    @RequestMapping("/selectInventoryByKeyword")
    @ResponseBody
    public Result selectInventoryByKeyword(String storeId,String keyword) {
    	
    	long startTime = System.currentTimeMillis();
    	
    	// keyword 
    	Result	result =  this.inventoryService.selectInventoryByKeyword( storeId, keyword);
	
    	
    	
    	long endTime = System.currentTimeMillis();
    	
    	System.out.println("耗时："+(endTime-startTime)+"ms");
    	
    	return result;
    }
    
    @RequestMapping("/updateInventory")
    @ResponseBody
    public Result updateInventory(Goods goods) {
    	
    	Long id = goods.getId();
    	
    	String inventory  = goods.getInventory();
    	
    	if(id==null||inventory==null){
    		
    		return Result.build(500, "更新库存失败，原因：参数不对");
    	}
    	
    	long startTime = System.currentTimeMillis();
    	
    	
    	Result result = inventoryService.updateInventory(goods);
    	
    	
    	long endTime = System.currentTimeMillis();
    	
    	System.out.println("耗时："+(endTime-startTime)+"ms");
    	
    	return result;
    }
	
}
