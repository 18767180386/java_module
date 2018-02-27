package com.aiiju.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.MCardMapper;
import com.aiiju.mapper.MemberMapper;
import com.aiiju.pojo.MCard;
import com.aiiju.pojo.Member;
import com.aiiju.store.service.MCardService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName: MCardServiceImpl
 * @Description: 会员卡 ServiceImpl
 * @author 小飞
 * @date 2016年11月12日 上午10:44:04
 *
 */
@Service("mcardService")
public class MCardServiceImpl implements MCardService {

    @Autowired
    private MCardMapper mcardMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Result save(MCard mCard) {
        mCard.setIsPast(Byte.valueOf("1"));
        this.mcardMapper.add(mCard);
        return Result.success(mCard.getId());
    }

    @Override
    public Result deleteById(Long id) {
        MCard mCard = this.mcardMapper.getById(id);
        if (mCard != null) {
            List<Member> memberList = this.memberMapper.getByMCardId(id);
            if (memberList == null || memberList.size() == 0) {
                this.mcardMapper.delete(id);
                return Result.success(true);
            } else {
                return Result.build(400, "该会员卡被用户正常使用中,不能删除", false);
            }
        }
        return Result.build(200, "该会员卡不存在", false);
    }

    @Override
    public Result update(MCard mCard) {
        if (mCard.getStatus() != null) {
            MCard dbCard = this.mcardMapper.getById(mCard.getId());
            if ("2".equals(dbCard.getIsPast().toString())) {
                return Result.build(401, "该会员卡已过期,无法进行修改");
            }
        }
        this.mcardMapper.update(mCard);
        return Result.success(true);
    }

    @Override
    public Result getById(Long id) {
        MCard card = this.mcardMapper.getById(id);
        return Result.success(card);
    }

    @Override
    public Result getAllByStoreId(String storeId, Integer currentNum, Integer pageSize) {
        PageHelper.startPage(currentNum, pageSize);
        List<MCard> list = this.mcardMapper.getAllByStoreId(storeId);
        return Result.success(list);
    }

    @Override
    public Result getWithObjectById(Long id) {
        MCard card = this.mcardMapper.getWithObjectById(id);
        return Result.success(card);
    }

    @Override
    public List<MCard> getLimitMCardList() {
        return this.mcardMapper.getLimitMCardList();
    }

    @Override
    public void updateBatch(List<MCard> taskList) {
        this.mcardMapper.updateBatch(taskList);
    }

}
