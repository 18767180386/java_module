package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.DealRate;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.DealRateService;
/**
 * 
 * @ClassName: DealRateController 
 * @Description: 交易费率控制器
 * @author 小飞 
 * @date 2016年12月19日 下午1:29:29 
 *
 */
@Controller
@RequestMapping("/dealRate")
public class DealRateController {

	private static Logger logger = Logger.getLogger(DealRateController.class);
	
	@Autowired
	private DealRateService dealRateService;
	
	@RequestMapping("/list")
	@ResponseBody
	public Result list() {
		try {
			return this.dealRateService.getDealRate();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Result save(DealRate dealRate) {
		try {
			return this.dealRateService.saveDealRate(dealRate);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(DealRate dealRate) {
		try {
			return this.dealRateService.updateDealRate(dealRate);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/remark")
	public String show() {
		return "dealRate/remark";
	}
}
