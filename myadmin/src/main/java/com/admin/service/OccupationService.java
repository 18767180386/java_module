package com.admin.service;

import com.admin.dto.Results;
import com.admin.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OccupationService extends IBaseService
{

    /**
     *
     *
     * @param args
     * @return
     */
    Results addOccupation(String args);

    /**
     *
     * @param item
     */
    void addOccupationPic(List<OccupationPicInfo> item);


    /**
     *
     *
     * @return
     */
     Results getOccupationList(String args);


    /**
     *
     * ]
     * @return
     */
    Results getOccupationTypeList(String args);


    /**
     *
     *
     * @param
     * @return
     */
    Results  addOccupationType(String args);


    /**
     *
     * 添加评论
     * @param
     * @return
     */
  //  Results  addOccupationComment(String args);

    /**
     * \
     *
     * @param
     * @return
     */
    Results addOccupationComment(String args);


   // Results addOccupationReply(String args);

    /**
     *
     *
     * @param
     * @return
     */
    Results addOccupationReply(String args);



   // Results getOccupationCommentList(@Param("occ_id") int occId,@Param("sorttype") int sorttype,@Param("offset") int offset, @Param("limit") int limit);


    /**
     *
     *
     * @param args
     * @return
     */
    Results  getOccupationCommentList(String args);


    /**
     *
     *
     * @param args
     * @return
     */
    Results  getOccupationReplyList(String args);


    /**
     *
     *
     * @param args
     * @return
     */
    Results  getCommentList(String args);



    Results  getOccupationById(String args);
}
