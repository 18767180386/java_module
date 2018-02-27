package com.admin.dao;

import com.admin.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OccupationDao {
    /**
     *
     * 添加文章
     * @param occupationInfo
     * @return
     */
    long addOccupation(OccupationInfo occupationInfo);


    /**
     *添加文章图片
     * @param item
     */
    void addOccupationPic(List<OccupationPicInfo> item);


    /**
     *
     * 获取文章列表
     * @param type
     * @param keyword
     * @param offset
     * @param limit
     * @return
     */
    List<OccupationInfo> getOccupationList(@Param("type") int type,@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);


    /**
     *
     * 获取职业类别列表
     * @return
     */
    List<OccupationTypeInfo> getOccupationTypeList();


    /**
     *
     * 添加职业信息类型
     * @param occupationTypeInfo
     * @return
     */
    int  addOccupationType(OccupationTypeInfo occupationTypeInfo);


    /**
     *
     * 添加评论
     * @param occupationCommentInfo
     * @return
     */
    int addOccupationComment(OccupationCommentInfo occupationCommentInfo);


    /**
     *
     * 添加评论
     * @param occupationReplyInfo
     * @return
     */
    int addOccupationReply(OccupationReplyInfo occupationReplyInfo);


    /**
     * 获取评论
     *
     * @param occId
     * @param offset
     * @param limit
     * @return
     */
    List<OccupationCommentInfo>  getOccupationCommentList(@Param("occ_id") int occId,@Param("sorttype") int sorttype,@Param("offset") int offset, @Param("limit") int limit);


    /**
     *
     * 获取回复
     * @param commentId
     * @param offset
     * @param limit
     * @return
     */
    List<OccupationReplyInfo>  getOccupationReplyList(@Param("comment_id") int commentId,@Param("offset") int offset, @Param("limit") int limit);


    /**
     *
     *
     *
     * @param occId
     * @param sorttype
     * @param offset
     * @param limit
     * @return
     */
    List<OccupationCommentInfo>  getCommentList(@Param("occ_id") int occId,@Param("sorttype") int sorttype,@Param("offset") int offset, @Param("limit") int limit);


    /**
     *
     *
     * @return
     */
    OccupationInfo  getOccupationById(@Param("occ_id") int occId);

}
