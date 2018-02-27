package com.aiiju.pay.business.zfb.hb;

/**
 * 
 * @ClassName: HbStatus 
 * @Description: 交易状态
 * @author 小飞 
 * @date 2016年12月3日 上午10:10:48 
 *
 */
public enum HbStatus {
     S // 交易成功（包括支付宝返回“处理中”）

    ,I // 支付宝返回处理中

    ,F // 支付宝返回失败

    ,P // POSP返回失败，或商户系统失败

    ,X // 建立连接异常

    ,Y // 报文上送异常

    ,Z // 报文接收异常

    ,C // 收银员取消
}
