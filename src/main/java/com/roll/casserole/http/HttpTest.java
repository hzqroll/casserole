package com.roll.casserole.http;

import com.alibaba.fastjson.JSONObject;
import com.roll.casserole.http.pool.HttpClientFactory;
import com.roll.casserole.http.pool.HttpRequestFactory;
import io.netty.util.CharsetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * <p>@author roll
 * <p>created on 2020/8/6 3:51 下午
 */
public class HttpTest {

    /**
     * 链接超时时间 60s
     */
    private static int connectTimeout = 60000;

    /**
     * 读取超时时间 60s
     */
    private static int socketTimeout = 60000;

    /**
     * 从链接池获取链接实例的超时时间
     */
    private static int connectionRequestTimeout = 60000;

    /**
     * 定义不活动时间段时间 60s
     */
    private static int validateAfterInactivityTime = 60000;

    /**
     * 链接失败重试次数
     */
    private static int retryTimes = 3;
    /**
     * 链接池最大大小
     */
    private static int maxPoolSize = 55;

    /**
     * 配置信息
     */
    private static RequestConfig requestConfig;

    /**
     * 链接池配置
     */
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    /**
     * 请求失败时,进行请求重试
     */
    private static HttpRequestRetryHandler handler;

    static {
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivityTime);
        poolingHttpClientConnectionManager.setMaxTotal(maxPoolSize);

        handler = (e, i, httpContext) -> {
            // 默认三次
            if (i > retryTimes) {
                //重试超过3次,放弃请求
                return false;
            }
            HttpClientContext context = HttpClientContext.adapt(httpContext);
            HttpRequest request = context.getRequest();
            //如果请求不是关闭连接的请求
            return !(request instanceof HttpEntityEnclosingRequest);
        };

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).build();
    }

    public static void main(String[] args) {
        String requestUrl = "http://127.0.0.1:8083/index";
        HttpEntityEnclosingRequestBase request = new HttpRequestFactory(requestUrl).getRequest();
        HttpClient client = HttpClientFactory.getDefaultClient(requestConfig, poolingHttpClientConnectionManager, handler);

        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setHeader("Connection", "keep-alive");
        request.setHeader("Keep-Alive", "3000");
        for (int i = 0; i < 3000; i++) {
            new Thread(() -> {
                try {
                    HttpResponse response = client.execute(request);
                    int responseCode = response.getStatusLine().getStatusCode();
                    System.out.println("responseCode: " + responseCode);
                    HttpEntity responseEntity = response.getEntity();
                    String res = EntityUtils.toString(responseEntity, CharsetUtil.UTF_8);
                    System.out.println("res: " + res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
