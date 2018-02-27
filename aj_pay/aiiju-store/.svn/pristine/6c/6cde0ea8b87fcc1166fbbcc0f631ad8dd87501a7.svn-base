package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.mapper.DealDetailMapper;
import com.aiiju.mapper.DealMapper;
import com.aiiju.mapper.PrinterMapper;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealDetail;
import com.aiiju.pojo.Printer;
import com.aiiju.store.service.PrintService;

/**
 * 
 * @ClassName: DealServiceImpl
 * @Description: 交易流水ServiceImpl
 * @author 小飞
 * @date 2016年11月14日 下午4:06:59
 *
 */
@Service("printService")
public class PrintServiceImpl implements PrintService {
	
	@Autowired
	private PrinterMapper printerMapper;
	@Override
	public Result getPrinterByStoreId(String printer_storeid) {
		
		
		List<Printer> list = printerMapper.getPrinterByStoreId(printer_storeid);
		
		if(list.size()>0){
			return Result.success();
		}else{
			System.out.println("很抱歉，未检测到您的打印设备");
			return Result.build(404, "很抱歉，未检测到您的打印设备");
		}
	
	}
	
	
	
	
}
