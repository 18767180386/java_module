package com.aiiju.store.scheduler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aiiju.common.util.Print365Util;
import com.aiiju.pojo.Deal;
import com.aiiju.store.service.DealService;
import com.github.pagehelper.StringUtil;

/**
 * 
 * @ClassName: PrintTask
 * @Description: 检测打印状态
 * @author 宗介
 * @date 2017年4月20日 上午13:00:23
 */
public class PrintTask  {


@Autowired
private DealService dealService;

private List<Deal> taskList = new ArrayList<Deal>();

public void checkStatus() throws ParseException{
   
    List<Deal> dealList = this.dealService.getPrintedStatusList();

    for (Deal deal : dealList) {
         String orderindex = 	deal.getPrintOrder();
    	  String printStatus = Print365Util.queryOrder(orderindex);
    	  
    	  if(StringUtil.isNotEmpty(printStatus)&&printStatus.contains("已打印")){
    		  deal.setPrintReceiveStatus("1");
    		  taskList.add(deal);
    	  }
    }
    if (taskList.size() > 0) {
        this.dealService.updateprintBatch(taskList);
        taskList = null;
    }
}
}
