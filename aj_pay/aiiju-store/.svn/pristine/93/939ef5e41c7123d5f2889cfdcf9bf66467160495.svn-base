package com.aiiju.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.MessageMapper;
import com.aiiju.pojo.Message;
import com.aiiju.store.service.MessageService;
/**
 * 
 * @ClassName: MessageServiceImpl 
 * @Description: 消息通知serviceImpl
 * @author 小飞 
 * @date 2016年11月8日 下午2:05:52 
 *
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public Result getMessagesByStoreId(String storeId, Integer currentNum, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
		params.put("storeId", storeId);
		params.put("index", (currentNum-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Message> list = messageMapper.getMessagePage(params);
		return Result.success(list);
	}

	@Override
	public Result getMessagesByOperatorId(String operatorId, Integer currentNum, Integer pageSize) {
		Map<String,Object> params = new HashMap<>();
		params.put("operatorId", operatorId);
		params.put("index", (currentNum-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Message> list = messageMapper.getMessagePage(params);
		return Result.success(list);
	}
}
