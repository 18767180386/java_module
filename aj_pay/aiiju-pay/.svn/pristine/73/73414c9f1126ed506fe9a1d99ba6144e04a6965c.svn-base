package com.aiiju.pay.business.wx.common.report;

import com.aiiju.pay.business.wx.common.report.protocol.ReportReqData;

/**
 * 
 * @ClassName: ReporterFactory 
 * @Description: 报告工厂
 * @author 小飞 
 * @date 2016年12月7日 下午4:50:54 
 *
 */
public class ReporterFactory {

    /**
     * 请求统计上报API
     * @param reportReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return 返回一个Reporter
     */
    public static Reporter getReporter(ReportReqData reportReqData){
        return new Reporter(reportReqData);
    }

}
