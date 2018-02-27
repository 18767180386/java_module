package com.aiiju.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.ProductMapper;
import com.aiiju.pojo.Product;
import com.aiiju.store.service.ProductService;

/**
 * 
 * @ClassName: ProductServiceImpl
 * @Description: APP产品 ServiceImpl
 * @author 小飞
 * @date 2017年1月6日 下午3:15:24
 *
 */
@Service("versionService")
public class ProductServiceImpl implements ProductService {

    @Value("${BASE_URL}")
    private String FILE_BASE_URL;
    
    @Value("${ANDROID_FILI_UPLOAD_PATH}")
    private String ANDROID_FILI_UPLOAD_PATH;
    

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result checkVersion(Byte type) {
        return Result.success(this.productMapper.getByType(type));
    }

    @Override
    public String getDownloadURL(Byte type) {
    	String url = productMapper.getUrlByType(type);
        return url;
    }

    @Override
    public void update(Product product) {
        this.productMapper.update(product);
    }


	@Override
	public String getAndroidDownloadPath() {
		
		return ANDROID_FILI_UPLOAD_PATH;
	}


}
