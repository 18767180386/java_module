package com.aiiju.mapper;

import java.util.List;

import com.aiiju.pojo.MCard;

/**
 * 
 * @ClassName: MCardMapper
 * @Description: 会员卡Mapper
 * @author 小飞
 * @date 2016年11月11日 下午5:15:49
 *
 */
public interface MCardMapper {

    public void add(MCard mCard);

    public void delete(Long id);

    public void update(MCard mCard);

    public MCard getById(Long id);

    public List<MCard> getAllByStoreId(String storeId);

    public List<MCard> getByMemberId(Long memberId);

    public MCard getWithObjectById(Long id);

    public List<MCard> getLimitMCardList();

    public void updateBatch(List<MCard> taskList);

}
