package com.roll.casserole.http.pool;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.roll.casserole.http.pool.HttpClientConstant.URL_ENCODED;

/**
 * 组装request和入参数据
 */
public class HttpRequestFactory {

    /**
     * 请求地址
     */
    private final String requestUrl;

    /**
     * 入参数据
     */
    private List<NameValuePair> nameValuePairList;

    public HttpRequestFactory(String requestUrl, List<NameValuePair> nameValuePairList) {
        this.requestUrl = requestUrl;
        this.nameValuePairList = nameValuePairList;
    }


    public HttpRequestFactory(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public HttpEntityEnclosingRequestBase getRequest() {
        HttpEntityEnclosingRequestBase request = new HttpPost(requestUrl);
        request.addHeader("Content-Type", "application/json;charset=utf-8");

        HttpEntity entity;
        if (nameValuePairList != null && nameValuePairList.size() > 0) {
            try {
                entity = new UrlEncodedFormEntity(nameValuePairList, URL_ENCODED);
                request.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return request;
    }
}
