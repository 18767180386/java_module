package com.admin.web;

import com.admin.dto.Results;
import com.admin.entity.OccupationCommentInfo;
import com.admin.entity.OccupationTypeInfo;
import com.admin.service.OccupationService;
import com.admin.service.UserInfoService;
import com.admin.util.ParameterUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/occupation")
public class OccupationController extends  ApiController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OccupationService occupationService;

    @RequestMapping(value = "/addOccupation", method = RequestMethod.POST)
    @ResponseBody
    public Results addOccupation(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return  occupationService.addOccupation(args);
    }

    @RequestMapping(value = "/getOccupationList", method = RequestMethod.POST)
    @ResponseBody
    public Results getOccupationList(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return  occupationService.getOccupationList(args);
    }


    @RequestMapping(value = "/getOccupationTypeList", method = RequestMethod.POST)
    @ResponseBody
    public Results getOccupationTypeList(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return  occupationService.getOccupationTypeList(args);
    }

    @RequestMapping(value = "/addOccupationType", method = RequestMethod.POST)
    @ResponseBody
    public Results addOccupationType(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return  occupationService.addOccupationType(args);
    }

    @RequestMapping(value = "/getOccupationCommentList", method = RequestMethod.POST)
    @ResponseBody
    public Results getOccupationCommentList(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return occupationService.getOccupationCommentList(args);
    }

    @RequestMapping(value = "/addOccupationComment", method = RequestMethod.POST)
    @ResponseBody
    public  Results addOccupationComment(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return occupationService.addOccupationComment(args);
    }


    @RequestMapping(value = "/addOccupationReply", method = RequestMethod.POST)
    @ResponseBody
    public  Results  addOccupationReply(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return  occupationService.addOccupationReply(args);
    }

    @RequestMapping(value = "/getOccupationReplyList", method = RequestMethod.POST)
    @ResponseBody
    public   Results getOccupationReplyList(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return occupationService.getOccupationReplyList(args);
    }


    @RequestMapping(value = "/getCommentList", method = RequestMethod.POST)
    @ResponseBody
    public Results getCommentList(HttpServletRequest request, HttpServletResponse response, String args)
    {

        return occupationService.getCommentList(args);
    }

    @RequestMapping(value = "/getOccupationById", method = RequestMethod.POST)
    @ResponseBody
    public Results getOccupationById(HttpServletRequest request, HttpServletResponse response, String args)
    {
        return occupationService.getOccupationById(args);
    }


}
