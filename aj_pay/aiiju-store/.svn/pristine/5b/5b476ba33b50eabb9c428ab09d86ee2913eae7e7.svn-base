package com.aiiju.store.controller.rest;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.MCard;
import com.aiiju.pojo.Member;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.MCardService;
import com.aiiju.store.service.MemberService;
import com.aiiju.store.service.ShopService;

/**
 * 
 * @ClassName: MemberController
 * @Description: 会员控制器
 * @author 小飞
 * @date 2016年11月22日 下午5:51:38
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    private static Logger logger = Logger.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MCardService mCardService;

    @Autowired
    private ShopService shopService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
    }
    

    
    
    
    

    @RequestMapping("/get")
    @ResponseBody
    public Result get(Long id) {
        try {
            return this.memberService.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result gets(String storeId, Integer currentNum, Integer pageSize) {
        try {
            return this.memberService.getAllByStoreId(storeId, currentNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    /**
     * 20170516废弃
     * @param member
     * @param mCardId
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Member member, Long mCardId) {
        try {
            return this.memberService.save(member, mCardId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    
    @RequestMapping("/saveMember")
    @ResponseBody
    public Result saveMember(Member member) {
        try {
        	if(member!=null&&member.getId()!=null&&member.getId()>0){
        		
        		return memberService.update(member);
        		
        	}else{
        		
        		return this.memberService.saveMember(member);
        	}
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/bindCard")
    @ResponseBody
    public Result bindCard(Long memberId, Long mCardId, String code) {
        try {
            return this.memberService.saveMCard(memberId, mCardId, code);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Member member) {
        try {
            return this.memberService.update(member);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/getByMCardId")
    @ResponseBody
    public Result getByMCardId(Long mCardId, Integer currentNum, Integer pageSize) {
        try {
            return this.memberService.getByMCardId(mCardId, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/getByCondition")
    @ResponseBody
    public Result getByCondition(String storeId, String name, String mCardId) {
        try {
            return this.memberService.getByCondition(storeId, name, mCardId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    // =======================买家领取会员卡相关===========================
    @RequestMapping("/saveUI")
    public String saveUI(Long mCardId, HttpServletRequest request) {
        Result rt = this.mCardService.getById(mCardId);
        MCard mCard = (MCard) rt.getData();
        String shopName = this.shopService.getShopName(mCard.getStoreId());
        request.setAttribute("shopName", shopName);
        request.setAttribute("mcard", mCard);
        return "/member/saveUI";
    }

    @RequestMapping("/saveByConsumer")
    @ResponseBody
    public Result saveByConsumer(Member member, Long mCardId) {
        try {
            return this.memberService.save(member, mCardId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/saveResult")
    public String saveResult(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "/member/saveResult";
    }

    @RequestMapping("/updateUI")
    public String updateUI(HttpServletRequest request) {
        String id = request.getParameter("id");
        Result member = this.memberService.getById(Long.parseLong(id));
        request.setAttribute("member", member.getData());
        return "/member/updateUI";
    }

    @RequestMapping("/updateResult")
    public String updateResult() {
        return "/member/updateResult";
    }
}
