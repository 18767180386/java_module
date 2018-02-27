package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.MessageService;
/**
 * 
 * @ClassName: MessageController 
 * @Description: 消息控制器
 * @author 小飞 
 * @date 2016年11月17日 上午9:50:41 
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {

	private static Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/list")
	@ResponseBody
	public Result list(String storeId,String operatorId,
			@RequestParam(value="currentNum",defaultValue="1") Integer currentNum,
			@RequestParam(value="pageSize",defaultValue="20") Integer pageSize) {
		try {
			Result result = null;
			if (operatorId == null) {
				result = this.messageService.getMessagesByStoreId(storeId,currentNum,pageSize);
			} else {
				result = this.messageService.getMessagesByOperatorId(operatorId,currentNum,pageSize);
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Result.build(500,WebConstant.SYS_ERROR);
		}
	}
}
