package com.aiiju.store.controller.rest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Goods;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.GoodsService;
/**
 * 
 * @ClassName: GoodsController 
 * @Description: 商品控制器
 * @author 小飞 
 * @date 2016年11月17日 上午10:54:09 
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

	private static Logger logger = Logger.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/get")
	@ResponseBody
	public Result get(Long id) {
		try {
			return this.goodsService.getById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/list")
	@ResponseBody
	public Result gets(Long goodsTypeId, Integer currentNum, Integer pageSize) {
		try {
			return this.goodsService.getAllByGoodsTypeId(goodsTypeId,currentNum,pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(Goods goods) {
		try {
			return this.goodsService.save(goods);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(Goods goods) {
		try {
			return this.goodsService.update(goods);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result deletes(String ids, String storeId) {
		try {
			String[] gids = ids.split(",");
			if (gids.length > 1) {
				return this.goodsService.deleteByIds(gids,storeId);
			}else {
				return this.goodsService.deleteById(Long.parseLong(ids));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Result upload(MultipartFile uploadFile,String storeId) {
		try {
			return this.goodsService.upload(uploadFile, storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
	
	/**
	 * 同步商品的信息
	 * @param storeId 总店的Id
	 * @return status 200 表示成功 401  表示失败
	 */
	@RequestMapping("/synchronize")
	@ResponseBody
	public Result updateSynchronize(String storeId) {
		try {
			return this.goodsService.updateSynchronize(storeId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(401,WebConstant.SYS_ERROR);
		}
	}
}
