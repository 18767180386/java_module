package com.aiiju.store.controller.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Product;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.ProductService;

/**
 * 
 * @ClassName: ProductController
 * @Description: 产品 控制器
 * @author 小飞
 * @date 2017年1月6日 下午3:08:55
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping("/checkVersion")
    @ResponseBody
    public Result checkVersion(Byte type) {
        try {
            return this.productService.checkVersion(type);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/download")
    @ResponseBody
    public Result download(Product product) {
        try {
            String url = this.productService.getDownloadURL(product.getType());
            return Result.success(url);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/downloadAndroid")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
    	String url = this.productService.getDownloadURL((byte) 1);
    	String fileName = url.replaceAll("https://store.ecbao.cn/app/", "");
        if (fileName != null) {
       
            File file = new File(productService.getAndroidDownloadPath()+"/"+fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    
   

	@RequestMapping("/update")
    @ResponseBody
    public Result update(Product product) {
        try {
            this.productService.update(product);
            return Result.success(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

}
