package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.JPush;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.JPushService;

/**
 * 
 * @ClassName: JPushController
 * @Description: 消息推送控制器
 * @author 小飞
 * @date 2016年12月20日 下午1:31:15
 *
 */
@Controller
@RequestMapping("/jpush")
public class JPushController {

    private static Logger logger = Logger.getLogger(JPushController.class);

    @Autowired
    private JPushService jpushService;

    /**
     * 绑定
     * 
     * @param jpush
     * @return
     */
    @RequestMapping("/bind")
    @ResponseBody
    public Result bind(JPush jpush) {
        try {
            return this.jpushService.save(jpush);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/getNotReadJpush")
    @ResponseBody
    public Result getNotReadJpush(JPush jpush) {
        try {
            return this.jpushService.getNotReadMsgByEquipmentCode(jpush.getEquipmentCode());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
}
