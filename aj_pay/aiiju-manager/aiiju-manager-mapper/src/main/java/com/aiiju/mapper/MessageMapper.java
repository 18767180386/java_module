package com.aiiju.mapper;

import java.util.List;
import java.util.Map;

import com.aiiju.pojo.Message;
/**
 * 
 * @ClassName: MessageMapper 
 * @Description: 消息通知 Mapper
 * @author 小飞 
 * @date 2016年11月8日 下午2:06:19 
 *
 */
public interface MessageMapper {
	/**
	 * 添加消息
	 * @param message
	 * @return
	 */
	public void add(Message message);
	/**
	 * 获取消息通知
	 * @param id 消息id
	 * @return
	 */
	public Message getById(Long id);
	/**
	 * 通过 shopId/operatorId 获取所有消息通知
	 * @param map
	 * @return
	 */
	public List<Message> getMessagePage(Map<String,Object> map);
	
}
