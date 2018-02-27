package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.mapper.BookingManageMapper;
import com.aiiju.mapper.BookingManageSwitchMapper;
import com.aiiju.pojo.BookingManage;
import com.aiiju.pojo.BookingManageSwitch;
import com.aiiju.pojo.JPush;
import com.aiiju.pojo.User;
import com.aiiju.store.service.BookingManageService;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: BookingManageServiceImpl 
 * @Description: 预约管理 ServiceImpl
 * @author 乔巴
 * @date 2017年08月23日 
 *
 */
@Service("bookingManageService")
public class BookingManageServiceImpl implements BookingManageService {
	
	@Autowired
	private BookingManageMapper bookingManageMapper;
	
	@Autowired
	private BookingManageSwitchMapper bookingManageSwitchMapper;
	

	@Override
	public List<Map<String, Object>> getBoookingManageList(String status,String startDate,String endDate,
			String phone, String keyWord,Integer pageNum, Integer pageSize,String storeId,String drawer) {
		PageHelper.startPage(pageNum, pageSize);
		return bookingManageMapper.getBoookingManageList(status,startDate, endDate, phone, keyWord,storeId,drawer);
	}

	@Override
	public List<Map<String, Object>> getBookingManageById(Long id) {
		return bookingManageMapper.getBookingManageById(id);
	}

	@Override
	public int updateById(BookingManage bookingManage) {
		return bookingManageMapper.updateById(bookingManage);
	}

	@Override
	public int deleteById(Long id) {
		return bookingManageMapper.deleteById(id);
	}

	@Override
	public List<BookingManage> getRecentTime(String storeId,String phone,Long id) {
		return bookingManageMapper.getRecentTime(storeId,phone,id);
	}

	@Override
	public int createOrder(BookingManage bookingManage) {
		return bookingManageMapper.createOrder(bookingManage);
	}
	@Override
	public Map<String, String> getTotalAmount(String status,String storeId,String startDate,String endDate,
			String phone,String keyWord,String drawer) {
		return bookingManageMapper.getTotalAmount(status,storeId,startDate,endDate,phone,keyWord,drawer);
	}

	@Override
	public List<Map<String, String>> getAllBeginAndReminderTime(String storeId) {
		return bookingManageMapper.getAllBeginAndReminderTime(storeId);
	}

	@Override
	public List<Map<String, String>> getDrawerEquipmentCode() {
		return bookingManageMapper.getDrawerEquipmentCode();
	}

	@Override
	public List<Map<String, String>> getDrawerAndCustomerCode() {
		return bookingManageMapper.getDrawerAndCustomerCode();
	}

	@Override
	public int bacthAddRemindTime(List<BookingManage> list) {
		return bookingManageMapper.bacthAddRemindTime(list);
	}

//	@Override
//	public List<Map<String, String>> getRemindStatus(String storeId) {
//		return bookingManageMapper.getRemindStatus(storeId);
//	}

	@Override
	public int bacthSetByRemindTime(List<BookingManage> list) {
		return bookingManageMapper.bacthSetByRemindTime(list);
	}

	@Override
	public int bacthSetByBeginTime(List<BookingManage> list) {
		return bookingManageMapper.bacthSetByBeginTime(list);
	}

	@Override
	public Map<String, Object> getIdBySendTime(String sendTime) {
		return bookingManageMapper.getIdBySendTime(sendTime);
	}

	@Override
	public Map<String, Object> getIdByBeginTime(String beginTime) {
		return bookingManageMapper.getIdByBeginTime(beginTime);
	}

	@Override
	public List<BookingManage> findAllBookingManage() {
		List<BookingManage> list = bookingManageMapper.findAllBookingManage();
		return list;
	}

	@Override
	public void updatePushsStatusById(long id) {
		bookingManageMapper.updatePushsStatusById(id);
	}
	
	@Override
	public Map<String, String> getAllRole(Integer userId) {
		return bookingManageMapper.getAllRole(userId);
	}

	@Override
	public void updateSendStatusById(long id) {
		bookingManageMapper.updateSendStatusById(id);
		
	}

	@Override
	public List<JPush> getJPushListByPhone(String phone) {
		List<JPush> list = bookingManageMapper.getJPushListByPhone(phone);
		return list;
	}

	@Override
	public List<User> getCustomerFromUserByStoreId(String storeId) {
		List<User> list = bookingManageMapper.getCustomerFromUserByStoreId(storeId);
		return list;
	}

	@Override
	public Result updateRemindSwitch(BookingManageSwitch bookingManageSwitch) {
		
		System.out.println("-------------");
		     String remind_status=   bookingManageMapper.getBookingManageSwitchByStoreId(bookingManageSwitch.getStoreId());
		     
		     if(remind_status==null){
		    	 // 新增
		    	 bookingManageSwitch.setCreateTime(new Date());
		    	 bookingManageMapper.saveBookingManageSwitch(bookingManageSwitch);
		    	 remind_status=   bookingManageMapper.getBookingManageSwitchByStoreId(bookingManageSwitch.getStoreId());
		     }else{
		    	 // 更新
		    	 bookingManageSwitch.setUpdateTime(new Date());
		    	 bookingManageMapper.updateBookingManageSwitch(bookingManageSwitch);
		     }
		
		return Result.success(remind_status);
	}

	@Override
	public BookingManageSwitch getRemindSwitch(BookingManage bookingManage) {
		
		BookingManageSwitch db=   bookingManageSwitchMapper.getBookingManageSwitchByStoreId(bookingManage.getStoreId());
		
		return db;
	}

	@Override
	public Map<String, String> getUserName(String phone) {
		return bookingManageMapper.getUserName(phone);
	}

	@Override
	public Result getList(BookingManage bookingManage,String startDate,String endTime,String searchDate,String keyWord, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		int index = ( (pageNum - 1) * pageSize);
		String phone = bookingManage.getPhone();
	//	String storeId = bookingManage.getStoreId();
		User user = bookingManageMapper.getUserByPhone(phone);
		String role =String.valueOf(user.getRole());
		if("0".equals(role)||"3".equals(role)){
			
			//可以看到当前店铺下的所有
		}
	     if("1".equals(role)||"2".equals(role)){
				
				//可以看到当前店铺下的所有
	    	 map.put("phone", phone);
		}
		String bookingStatus = bookingManage.getBookingStatus();
		String drawer = bookingManage.getDrawer();

		map.put("storeId", bookingManage.getStoreId());
		map.put("bookingStatus", bookingStatus);
		map.put("drawer", drawer);
		Map<String,String> mapDate = getDateByStr(searchDate, startDate, endTime);
		
		map.put("beginTime", mapDate.get("beginTime"));
		map.put("endTime",  mapDate.get("endTime"));

		map.put("keyWord", keyWord);
		map.put("index", index);
		map.put("pageSize", pageSize);


		List<Map<String, Object>> list = bookingManageMapper.getList(map);

		Map<String,Object> returnMap = new HashMap<String,Object>();
		 
		String totalAmount = bookingManageMapper.getTotalAmountNew(map);
		
		if(totalAmount==null){
			totalAmount="0.00";
		}
		 
		 returnMap.put("recordCount", list==null?0:list.size());
		 returnMap.put("totalAmount", totalAmount);
		 
		 List returnlist = parseData(list, map);
		// returnMap.put("list", value)
		 returnMap.put("list", returnlist);
		return Result.success(returnMap);
	}
	
	
    private List<Map<String, Object>> parseData(List<Map<String, Object>> source,Map<String,Object> params) {
        List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
        if (source == null || source.size() == 0) {
            return rt;
        }
        // 日期分组
        SortedMap<String, List<Map<String, Object>>> dataMap = new TreeMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> temp = null;
        for (Map<String, Object> data : source) {
          
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           
            String createDate = sdf.format(data.get("beginTime"));
            if (dataMap.get(createDate) != null) {
                dataMap.get(createDate).add(data);
            } else {
                temp = new ArrayList<Map<String, Object>>();
                temp.add(data);
                dataMap.put(createDate, temp);
            }
        }
        // 转换格式
        SortedMap<String, Object> rtMap = null;

        for (Map.Entry<String, List<Map<String, Object>>> entry : dataMap.entrySet()) {
            rtMap = new TreeMap<String, Object>();
            rtMap.put("createTime", entry.getKey());
            params.put("dateTime", entry.getKey());//"%Y-%m-%d"
            String  allTotal = bookingManageMapper.getDayTotal(params);
	         rtMap.put("allTotal", allTotal);
	         rtMap.put("bookingManageList", entry.getValue());
	         rt.add(rtMap);
        }
        return rt;
    }
	
	
	public Map<String,String> getDateByStr(String str,String startDate,String endTime){
		
		Map<String,String> map = new HashMap<String,String>();
		
		if("3".equals(str)){
			map.put("beginTime", DateUtil.getAnyDate(-7));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("0".equals(str)){
			map.put("beginTime", DateUtil.getAnyDate(0));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("1".equals(str)){
			map.put("beginTime", DateUtil.getAnyDate(-1));
			map.put("endTime", DateUtil.getAnyDate(-1));
		}else if("2".equals(str)){
			map.put("beginTime", DateUtil.getThisWeekStartAndEnd().get("startTime"));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("4".equals(str)){
			map.put("beginTime", DateUtil.getThisMonthStartAndEndDay().get("startTime"));
			map.put("endTime", DateUtil.getThisMonthStartAndEndDay().get("endTime"));
		}else if("5".equals(str)){
			map.put("beginTime", DateUtil.getLastWeekStartAndEnd().get("startTime"));
			map.put("endTime", DateUtil.getLastWeekStartAndEnd().get("endTime"));
		}else if("6".equals(str)){
			map.put("beginTime", startDate);
			map.put("endTime", endTime);
		}else{
//			System.out.println("没有日期");
//			map.put("beginTime", DateUtil.getAnyDate(-7));
//			map.put("endTime", DateUtil.getAnyDate(0));
		}
		return map;
	}


	@Override
	public 	List<Map<String,Object>> getCount(String status, String startDate, String endDate, String phone, String keyWord, Integer pageNum,
			Integer pageSize, String storeId, String drawer) {
		return bookingManageMapper.getCount(status, startDate, endDate, phone, keyWord, storeId, drawer);
	}


}
