package com.aiiju.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aiiju.pojo.Member;
import com.aiiju.pojo.MemberCouponLink;
import com.aiiju.pojo.MemberMCardLink;

/**
 * 
 * @ClassName: MemberMapper
 * @Description: 会员 Mapper
 * @author 小飞
 * @date 2016年11月11日 下午4:46:39
 *
 */
public interface MemberMapper {

    public void add(Member member);

    /**
     * 添加 会员和优惠券的关联关系
     * 
     * @param mcLink
     */
    public void addMCLink(MemberCouponLink mcLink);

    /**
     * 删除 会员和优惠券的关联关系
     * 
     * @param mcLink
     */
    public void deleteMCLink(MemberCouponLink mcLink);

    /**
     * 添加 会员和会员卡的关联关系
     * 
     * @param mmLink
     */
    public void addMMLink(MemberMCardLink mmLink);

    /**
     * 删除 会员和会员卡的关联关系
     * 
     * @param mmLink
     */
    public void deleteMMLink(MemberMCardLink mmLink);

    public void update(Member member);

    public Member getById(Long id);

    
    
    
    /**
     * 通过会员卡类型获取会员列表
     * 
     * @param mCardId
     * @return
     */
    public List<Member> getByMCardId(Long mCardId);

    public List<Member> getAllByStoreId(String storeId);

    /**
     * 手机查询
     * 
     * @param map
     * @return
     */
    public List<Member> getByPhone(@Param("storeId")String storeId, @Param("phone")String phone);

    public List<Member> getByName(@Param("storeId")String storeId, @Param("name")String name);
    
    public List<Member> getBykeyword(@Param("storeId")String storeId, @Param("name")String name);
    
    
    
}
