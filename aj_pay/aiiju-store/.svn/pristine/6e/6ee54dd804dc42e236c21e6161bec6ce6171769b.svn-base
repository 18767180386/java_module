package com.aiiju.store.scheduler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.aiiju.common.util.DateUtil;
import com.aiiju.pojo.MCard;
import com.aiiju.store.service.MCardService;

/**
 * 
 * @ClassName: MCardTask
 * @Description: 检测会员卡状态
 * @author 小飞
 * @date 2017年2月23日 上午10:47:23
 */
public class MCardTask {

    @Autowired
    private MCardService mcardService;

    public void checkStatus() throws ParseException {
        List<MCard> taskList = new ArrayList<MCard>();
        List<MCard> mcardList = this.mcardService.getLimitMCardList();
        // 当前日期
        DateTime dateTime = new DateTime();
        String str = dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth();
        Date date = DateUtil.parseStr(str, "yyyy-MM-dd");
        for (MCard mCard : mcardList) {
            // 要生效的数据
            if (date.equals(mCard.getStartDate())) {
                mCard.setStatus(Byte.valueOf("1"));// 设置生效
                mCard.setIsPast(Byte.valueOf("1"));
                taskList.add(mCard);
            } // 要过期的数据
            else if (mCard.getEndDate().before(date)) {
                mCard.setStatus(Byte.valueOf("2"));// 设置失效
                mCard.setIsPast(Byte.valueOf("2"));// 设置过期
                taskList.add(mCard);
            }
        }
        if (taskList.size() > 0) {
            this.mcardService.updateBatch(taskList);
            taskList = null;
        }
    }
}
