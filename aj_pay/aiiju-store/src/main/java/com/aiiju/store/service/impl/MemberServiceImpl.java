package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.mapper.MPointsDetailMapper;
import com.aiiju.mapper.MPointsMapper;
import com.aiiju.mapper.MemberMapper;
import com.aiiju.pojo.MCard;
import com.aiiju.pojo.MPoints;
import com.aiiju.pojo.Member;
import com.aiiju.pojo.MemberMCardLink;
import com.aiiju.store.service.MemberService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName: MemberServiceImpl
 * @Description: 会员 ServiceImpl
 * @author 小飞
 * @date 2016年11月12日 上午11:11:36
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    
    
    @Autowired
    private MPointsMapper mPointsMapper;

    @Autowired
    private MPointsDetailMapper mPonitsDetailMapper;
    
    @Override
    public Result saveMember(Member member) {
        List<Member> memberList = this.memberMapper.getByPhone(member.getStoreId(), member.getPhone());
        Member mb = null;
        Map<String, String> map = new HashMap<String, String>();
        if (memberList == null || memberList.size() == 0) {
            if (StringUtils.isBlank(member.getName())) {
                member.setName(member.getPhone());
            }
            // 保存会员
            this.memberMapper.add(member);
            map.put("id", member.getId()+"");
            map.put("type", "new");
        } else {
            mb = memberList.get(0);
            member.setId(mb.getId());
            map.put("id", member.getId()+"");
            map.put("type", "old");
        }

        return Result.success(map);
    }
    
    @Override
    public Result save(Member member, Long mCardId) {
        List<Member> memberList = this.memberMapper.getByPhone(member.getStoreId(), member.getPhone());
        Member mb = null;
        Map<String, String> map = new HashMap<String, String>();
        if (memberList == null || memberList.size() == 0) {
            if (StringUtils.isBlank(member.getName())) {
                member.setName(member.getPhone());
            }
            // 保存会员
            this.memberMapper.add(member);
            map.put("id", member.getId() + "");
            map.put("type", "new");
        } else {
            mb = memberList.get(0);
            member.setId(mb.getId());
            map.put("type", "old");
        }
//        // 判断是否已经领取会员卡
//        if (mb != null) {
//            List<MCard> cardList = mb.getCardList();
//            if (cardList != null) {
//                for (MCard mCard : cardList) {
//                    if (mCard.getId().intValue() == mCardId.intValue()) {
//                        return Result.build(401, "该会员已领取[" + mCard.getName() + "]的会员卡");
//                    }
//                }
//            }
//        }
//        map.put("id", member.getId() + "");
//        // 关联会员卡
//        MemberMCardLink mmLink = new MemberMCardLink();
//        mmLink.setMemberId(member.getId());
//        mmLink.setmCardId(mCardId);
//        mmLink.setCode(System.currentTimeMillis() + RandomUtil.generateMcardCode());
//        this.memberMapper.addMMLink(mmLink);
        return Result.success(map);
    }

    @Override
    public Result update(Member member) {
    	
    	   if (StringUtils.isBlank(member.getName())) {
               member.setName(member.getPhone());
           }
    	
        this.memberMapper.update(member);
        return Result.success(true);
    }

    @Override
    public Result getById(Long id) {
        Member member = this.memberMapper.getById(id);
        
        MPoints points = this.mPointsMapper.getByStoreId(member.getStoreId());
       
        Integer sum =0;
        
        String flag ="close";
        
        if(points!=null&&points.getStatus().equals("1")){
        	Map params = new HashMap();
    		params.put("phone", member.getPhone());
    		params.put("storeId", member.getStoreId());
        	 sum  = mPonitsDetailMapper.getValidPoints(params);
        	if(sum==null){
        		sum=0;
        	}
        	 System.out.println(sum);
        	 flag ="open";
        }else{
        	flag ="close";
        	System.out.println("该商铺没有设置会员积分或没打开积分开关");
        	return Result.build(200, "获取成功", member, "false");
        }
        
        
        
        return Result.build(200, "获取成功", member, sum+"");
    }

    @Override
    public Result getByMCardId(Long mCardId, Integer currentNum, Integer pageSize) {
        PageHelper.startPage(currentNum, pageSize);
        List<Member> list = this.memberMapper.getByMCardId(mCardId);
        return Result.success(list);
    }

    @Override
    public Result getAllByStoreId(String storeId, Integer currentNum, Integer pageSize) {
        PageHelper.startPage(currentNum, pageSize);
        List<Member> list = this.memberMapper.getAllByStoreId(storeId);
        return Result.success(list);
    }

    @Override
    public Result saveMCard(Long memberId, Long mCardId, String code) {
        Member member = this.memberMapper.getById(memberId);
        if (member != null) {
            List<MCard> cardList = member.getCardList();
            if (cardList != null) {
                for (MCard mCard : cardList) {
                    if (mCard.getId().intValue() == mCardId.intValue()) {
                        return Result.build(401, "该会员已领取[" + mCard.getName() + "]的会员卡");
                    }
                }
            }
            // 关联会员卡
            MemberMCardLink mmLink = new MemberMCardLink();
            mmLink.setMemberId(member.getId());
            mmLink.setmCardId(mCardId);
            mmLink.setCode(code);
            this.memberMapper.addMMLink(mmLink);
            return Result.success(member.getId());
        }
        return Result.build(401, "绑定会员卡失败");
    }

    @Override
    public Result getByCondition(String storeId, String name, String mCardId) {
        List<Member> memberList = null;

        if(!StringUtils.isBlank(name)){
        	  memberList = this.memberMapper.getBykeyword(storeId, name);
        }else{
        	
        }

        if(!StringUtils.isBlank(mCardId)){
        	  List<Member> dbList = this.memberMapper.getByMCardId(Long.parseLong(mCardId));
              memberList = new ArrayList<Member>(dbList.size());
              for (Member member : dbList) {
                  if (member.getPhone().contains(name)) {
                      memberList.add(member);
                  }
              }
        }
        
 
        return Result.success(memberList);
    }

}
