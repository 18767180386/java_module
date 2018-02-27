package com.aiiju.pay.business.wx.common.report;

import com.aiiju.pay.business.wx.common.report.protocol.ReportReqData;
import com.aiiju.pay.business.wx.common.report.service.ReportService;

/**
 * 
 * @ClassName: Reporter 
 * @Description: 报告人
 * @author 小飞 
 * @date 2016年12月7日 下午4:51:14 
 *
 */
public class Reporter {

    private ReportRunable r;
    private Thread t;
    private ReportService rs;

    /**
     * 请求统计上报API
     * @param reportReqData 这个数据对象里面包含了API要求提交的各种数据字段
     */
    public Reporter(ReportReqData reportReqData){
        rs = new ReportService(reportReqData);
    }

    public void run(){
        r = new ReportRunable(rs);
        t = new Thread(r);
        t.setDaemon(true);  //后台线程
        t.start();
    }
}
