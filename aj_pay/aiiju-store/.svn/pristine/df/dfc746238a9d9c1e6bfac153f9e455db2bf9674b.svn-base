package com.aiiju.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.CategoryMapper;
import com.aiiju.pojo.Category;
import com.aiiju.store.service.CategoryService;
/**
 * 
* @ClassName: CategoryServiceImpl 
* @Description: 类目ServiceImpl
* @author 小飞 
* @date 2017年3月22日 上午10:14:06
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public Result save(Category category) {
        this.categoryMapper.add(category);
        return Result.success(category.getId());
    }

    @Override
    public Result deleteById(Long id) {
        this.categoryMapper.deleteById(id);
        return Result.success(true);
    }

    @Override
    public Result getAllByStoreId(String storeId) {
        List<Category> list = this.categoryMapper.getAllByStoreId(storeId);
        return Result.success(list);
    }

}
