package com.aiiju.store.service;

import java.util.List;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.MCard;

/**
 * 
 * @ClassName: MCardService
 * @Description: 会员卡 Service
 * @author 小飞
 * @date 2016年11月12日 上午10:42:35
 *
 */
public interface MCardService {

    public Result save(MCard card);

    public Result deleteById(Long id);

    public Result update(MCard card);

    public Result getById(Long id);

    public Result getAllByStoreId(String storeId, Integer currentNum, Integer pageSize);
    /**
     * 级联查询
     * @param id
     * @return
     */
    public Result getWithObjectById(Long id);
    /**
     * 获取有期限会员卡列表(有期限，未过期)
     * @return
     */
    public List<MCard> getLimitMCardList();
    /**
     * 批量更新
     * @param taskList
     */
    public void updateBatch(List<MCard> taskList);

}
