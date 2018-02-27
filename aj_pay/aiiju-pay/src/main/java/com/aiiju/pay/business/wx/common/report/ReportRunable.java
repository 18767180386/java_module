package com.aiiju.pay.business.wx.common.report;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import com.aiiju.pay.business.wx.common.report.service.ReportService;

/**
 * 
 * @ClassName: ReportRunable 
 * @Description: 报告线程
 * @author 小飞 
 * @date 2016年12月7日 下午4:50:35 
 *
 */
public class ReportRunable implements Runnable {

    private ReportService reportService ;

    ReportRunable(ReportService rs){
        reportService = rs;
    }

    @Override
    public void run() {
        try {
            reportService.request();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
