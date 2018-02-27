package com.aiiju.pay.business.qq.listener;

import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundResData;
import com.aiiju.pay.business.qq.business.QqRefundBusiness;
/**
 * 
 * @ClassName: DefaultRefundBusinessResultListener 
 * @Description: 退款
 * @author 小飞 
 * @date 2017年2月13日 上午10:55:19 
 *
 */
public class QqRefundBusinessResultListener implements QqRefundBusiness.ResultListener {

    public static final String ON_FAIL_BY_RETURN_CODE_ERROR = "on_fail_by_return_code_error";
    public static final String ON_FAIL_BY_RETURN_CODE_FAIL = "on_fail_by_return_code_fail";
    public static final String ON_FAIL_BY_SIGN_INVALID = "on_fail_by_sign_invalid";
    public static final String ON_REFUND_FAIL = "on_refund_fail";
    public static final String ON_REFUND_SUCCESS = "on_refund_success";

    private String result = "";

    @Override
    public void onFailByReturnCodeError(QqRefundResData refundResData) {
        result = ON_FAIL_BY_RETURN_CODE_ERROR;
    }

    @Override
    public void onFailByReturnCodeFail(QqRefundResData refundResData) {
        result = ON_FAIL_BY_RETURN_CODE_FAIL;
    }

    @Override
    public void onFailBySignInvalid(QqRefundResData refundResData) {
        result = ON_FAIL_BY_SIGN_INVALID;
    }

    @Override
    public void onRefundFail(QqRefundResData refundResData) {
        result = ON_REFUND_FAIL;
    }

    @Override
    public void onRefundSuccess(QqRefundResData refundResData) {
        result = ON_REFUND_SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
