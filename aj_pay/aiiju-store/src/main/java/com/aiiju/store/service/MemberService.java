package com.aiiju.store.service;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Member;

/**
 * 
 * @ClassName: MemberService
 * @Description: 会员 Service
 * @author 小飞
 * @date 2016年11月11日 下午4:45:34
 *
 */
public interface MemberService {

    public Result save(Member member, Long mCardId);
    
    public Result saveMember(Member member);


    
    
    public Result update(Member member);

    public Result getById(Long id);

    /**
     * 通过会员卡id获取会员列表
     * 
     * @param map
     * @throws Exception
     */
    public Result getByMCardId(Long mCardId, Integer currentNum, Integer pageSize);

    /**
     * 通过店铺编号获取会员列表
     * 
     * @return
     * @throws Exception
     */
    public Result getAllByStoreId(String storeId, Integer currentNum, Integer pageSize);

    /**
     * 绑定会员卡
     * @param memberId
     * @param mCardId
     */
    public Result saveMCard(Long memberId, Long mCardId, String code);

    public Result getByCondition(String storeId, String name, String mCardId);

}
