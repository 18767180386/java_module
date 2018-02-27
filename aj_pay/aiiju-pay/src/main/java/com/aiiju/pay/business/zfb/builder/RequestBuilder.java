package com.aiiju.pay.business.zfb.builder;

import com.aiiju.pay.business.zfb.util.GsonFactory;
/**
 * 
 * @ClassName: RequestBuilder 
 * @Description: 请求父类
 * @author 小飞 
 * @date 2016年12月3日 上午10:08:25 
 *
 */
public abstract class RequestBuilder {
    private String appAuthToken;
    private String notifyUrl;

    /**
     * 验证请求对象
     * @return
     */
    public abstract boolean validate();

    /**
     * 获取bizContent对象，用于下一步转换为json字符串
     * @return
     */
    public abstract Object getBizContent();

    /**
     * 将bizContent对象转换为json字符串
     * @return
     */
    public String toJsonString() {
        // 使用gson将对象转换为json字符串
        return GsonFactory.getGson().toJson(this.getBizContent());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestBuilder{");
        sb.append("appAuthToken='").append(appAuthToken).append('\'');
        sb.append(", notifyUrl='").append(notifyUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public RequestBuilder setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public RequestBuilder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
