package com.aiiju.pay.business.wx.protocol.downloadbill_protocol;

/**
 * 
 * @ClassName: DownloadBillResData 
 * @Description: 下载对账单返回
 * @author 小飞 
 * @date 2016年12月7日 下午3:56:04 
 *
 */
public class DownloadBillResData {

    //协议层
    private String return_code = "";
    private String return_msg = "";

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
