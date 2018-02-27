package com.admin.service.impl;

import com.admin.dao.OccupationDao;
import com.admin.dao.ResourceDao;
import com.admin.dto.Results;
import com.admin.entity.*;
import com.admin.service.OccupationService;
import com.admin.util.ParameterUtil;
import com.admin.util.Utils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OccupationImpl implements OccupationService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OccupationDao occupationDao;
    /**
     *
     * 发布职业说
     * @param
     * @return
     */
    @Override
    public Results addOccupation(String args)
    {
        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            String title = map.get("title").toString();
            String content = map.get("content").toString();
            String userId = map.get("user_id").toString();
            String author = map.get("author").toString();
            String msgtype="0";
            if(map.containsKey("type"))
            {
                msgtype=map.get("type").toString();
            }
            String pic="";
            if(map.containsKey("pic")) {
               pic = map.get("pic").toString();
            }
            OccupationInfo occupationInfo = new OccupationInfo();
            occupationInfo.setAuthor(author);
            occupationInfo.setTitle(title);
            occupationInfo.setContent(content);
            occupationInfo.setUserId(Integer.valueOf(userId));
            occupationInfo.setAuthor(author);
            occupationInfo.setCreatetime(new Date());
            occupationInfo.setMsgType(Integer.valueOf(msgtype));
            long id = occupationDao.addOccupation(occupationInfo);
            int o_id=occupationInfo.getId();
            if (id <= 0) {
                return Results.build(-301, ADD_FAIL);
            }
            if (!Utils.isEmpty(pic)) {
                Type type = new TypeReference<List<PicModel>>() {
                }.getType();
                List<PicModel> list = JSON.parseObject(pic, type);
                if (list != null && list.size() > 0) {
                    List<OccupationPicInfo> oList = new ArrayList<OccupationPicInfo>();
                    for (PicModel p : list) {
                        OccupationPicInfo occupationPicInfo = new OccupationPicInfo();
                        occupationPicInfo.setOccupationId(Integer.valueOf(o_id + ""));
                        occupationPicInfo.setPicUrl(p.getPicUrl());
                        occupationPicInfo.setThumbnailUrl(p.getThumbnailUrl());
                        occupationPicInfo.setAddtime(new Date());
                        oList.add(occupationPicInfo);
                    }
                    occupationDao.addOccupationPic(oList);
                }
            }
            return Results.build(HTTP_SUCC_STATUS, ADD_SUCC);
        }catch (Exception e)
        {
            return Results.build(-302, ADD_FAIL);
        }
    }


    /**
     * 上传图片
     * @param item
     */
    @Override
    public   void addOccupationPic(List<OccupationPicInfo> item)
    {
         occupationDao.addOccupationPic(item);
    }

    /**
     * 获取职业信息
     * @return
     */
    @Override
    public  Results getOccupationList(String args)
    {
        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            int offset = (map.get("offset") == null) ? 1 : Integer.valueOf(map.get("offset").toString());// 默认便宜0
            int limit = (map.get("limit") == null) ? 20 : Integer.valueOf(map.get("limit").toString());// 默认展示50条
            int type = (map.get("type") == null) ? 1 : Integer.valueOf(map.get("type").toString());
            String keyword="";
            if(map.containsKey("keyword"))
            {
                keyword=map.get("keyword").toString();
            }
            offset = (offset - 1) * limit;
            List<OccupationInfo> list=occupationDao.getOccupationList(type,keyword,offset,limit);
            return  Results.build(HTTP_SUCC_STATUS,GET_SUCC,list);
        }catch (Exception e)
        {
            return  Results.build(-001,GET_FAIL);
        }
    }

    /**
     *
     * 获取职业类型
     * @param args
     * @return
     */
    @Override
    public   Results getOccupationTypeList(String args)
    {
        try
        {
            List<OccupationTypeInfo> list=occupationDao.getOccupationTypeList();
            return  Results.build(HTTP_SUCC_STATUS,GET_SUCC,list);
        }catch (Exception e)
        {
            return  Results.build(-001,GET_FAIL);
        }
    }


    /**
     *
     * 添加评论
     * @param args
     * @return
     */
    @Override
   public   Results  addOccupationType(String args)
   {
       try {
           Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
           String sort_type=map.get("sort_type").toString();
           String sort_name=map.get("sort_name").toString();
           String sort_desc=map.get("sort_desc").toString();
           OccupationTypeInfo occupationTypeInfo=new OccupationTypeInfo();
           occupationTypeInfo.setSortType(Integer.valueOf(sort_type));
           occupationTypeInfo.setSortName(sort_name);
           occupationTypeInfo.setSortDesc(Integer.valueOf(sort_desc));
           int id=occupationDao.addOccupationType(occupationTypeInfo);
           if(id>0)
           {
             return   Results.build(HTTP_SUCC_STATUS,ADD_SUCC);
           }
           return  Results.build(-002,ADD_FAIL);
       }catch (Exception e)
       {
           return  Results.build(-001,ADD_FAIL);
       }
   }


   @Override
   public  Results addOccupationComment(String args)
   {
       /**
        *  private int commentId;
        private String commentContent;
        private int commentUid;
        private int commentNum;
        private Date commentTime;
        private int praiseNum;
        private int occupation_id;
        *
        *
        */

       try {
           Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
           String content=map.get("comment_content").toString();
           String comment_uid=map.get("comment_uid").toString();
           String occupation_id=map.get("occupation_id").toString();
           OccupationCommentInfo occupationCommentInfo=new OccupationCommentInfo();
           occupationCommentInfo.setCommentContent(content);
           occupationCommentInfo.setCommentUid(Integer.valueOf(comment_uid));
           occupationCommentInfo.setOccupation_id(Integer.valueOf(occupation_id));
           occupationCommentInfo.setCommentTime(new Date());
           occupationCommentInfo.setCommentNum(1);
           occupationCommentInfo.setPraiseNum(1);
           int id=occupationDao.addOccupationComment(occupationCommentInfo);
           if(id>0)
           {
               return   Results.build(HTTP_SUCC_STATUS,ADD_SUCC);
           }
           return  Results.build(-002,ADD_FAIL);
       }catch (Exception e)
       {
           return  Results.build(-001,ADD_FAIL);
       }
   }


    @Override
    public  Results addOccupationReply(String args)
    {
        /**
         *  private  int replyId;
         private String replyContent;
         private int replyUid;
         private Date replyTime;
         private int replyNum;
         private int replyToUid;
         private int com_id;
         *
         */

        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            String content=map.get("reply_content").toString();
            String reply_uid=map.get("reply_uid").toString();
            String reply_to_id=map.get("reply_to_id").toString();
            String comment_id=map.get("comment_id").toString();

            OccupationReplyInfo  occupationReplyInfo=new OccupationReplyInfo();
            occupationReplyInfo.setReplyContent(content);
            occupationReplyInfo.setCom_id(Integer.valueOf(comment_id));
            occupationReplyInfo.setReplyNum(1);
            occupationReplyInfo.setReplyTime(new Date());
            occupationReplyInfo.setReplyUid(Integer.valueOf(reply_uid));
            occupationReplyInfo.setReplyToUid(Integer.valueOf(reply_to_id));
            int id=occupationDao.addOccupationReply(occupationReplyInfo);
            if(id>0)
            {
                return   Results.build(HTTP_SUCC_STATUS,ADD_SUCC);
            }
            return  Results.build(-002,ADD_FAIL);
        }catch (Exception e)
        {
            return  Results.build(-001,ADD_FAIL);
        }

    }


    @Override
    public Results getOccupationCommentList(String args)
    {
        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            int offsets = (map.get("offset") == null) ? 1 : Integer.valueOf(map.get("offset").toString());// 默认便宜0
            int limits = (map.get("limit") == null) ? 20 : Integer.valueOf(map.get("limit").toString());// 默认展示50条
            int types = (map.get("sort_type") == null) ? 1 : Integer.valueOf(map.get("sort_type").toString());
            int occ_ids = (map.get("occ_id") == null) ? 1 : Integer.valueOf(map.get("occ_id").toString());
            offsets = (offsets - 1) * limits;
            List<OccupationCommentInfo>  lists=occupationDao.getOccupationCommentList(occ_ids,types,offsets,limits);
            return   Results.build(HTTP_SUCC_STATUS,GET_SUCC,lists);
        }catch (Exception e)
        {
            return  Results.build(-002,GET_FAIL);
        }
    }

    @Override
    public   Results getOccupationReplyList(String args)
    {
        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            int offset = (map.get("offset") == null) ? 1 : Integer.valueOf(map.get("offset").toString());// 默认便宜0
            int limit = (map.get("limit") == null) ? 20 : Integer.valueOf(map.get("limit").toString());// 默认展示50条
            int comment_id = (map.get("comment_id") == null) ? 1 : Integer.valueOf(map.get("comment_id").toString());
            offset = (offset - 1) * limit;
            List<OccupationReplyInfo>  list=occupationDao.getOccupationReplyList(comment_id,offset,limit);
            return   Results.build(HTTP_SUCC_STATUS,GET_SUCC,list);
        }catch (Exception e)
        {
            return  Results.build(-001,GET_FAIL);
        }
    }

    @Override
    public Results getCommentList(String args)
    {
        try {
            Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
            int offset = (map.get("offset") == null) ? 1 : Integer.valueOf(map.get("offset").toString());// 默认便宜0
            int limit = (map.get("limit") == null) ? 20 : Integer.valueOf(map.get("limit").toString());// 默认展示50条
            int type = (map.get("sort_type") == null) ? 1 : Integer.valueOf(map.get("sort_type").toString());
            int occ_id = (map.get("occ_id") == null) ? 1 : Integer.valueOf(map.get("occ_id").toString());
            offset = (offset - 1) * limit;
            List<OccupationCommentInfo>  list=occupationDao.getCommentList(occ_id,type,offset,limit);
            return   Results.build(HTTP_SUCC_STATUS,GET_SUCC,list);
        }catch (Exception e)
        {
            return  Results.build(-001,GET_FAIL);
        }
    }


    @Override
   public  Results  getOccupationById(String args)
   {
       try {
           Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
           int occ_id = (map.get("occ_id") == null) ? 1 : Integer.valueOf(map.get("occ_id").toString());
           OccupationInfo  list=occupationDao.getOccupationById(occ_id);
           return   Results.build(HTTP_SUCC_STATUS,GET_SUCC,list);
       }catch (Exception e)
       {
           return  Results.build(-001,GET_FAIL);
       }
   }


}
