package com.roll.casserole.http.pool;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 获取{@link org.apache.http.client.HttpClient}
 */
public class HttpClientFactory {

    /**
     * 提供默认的client， 单例
     *
     * @return client
     */
    public static CloseableHttpClient getDefaultClient(RequestConfig requestConfig, PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, HttpRequestRetryHandler handler) {
        return HttpClientUtil.getDefaultHttpClient(requestConfig, poolingHttpClientConnectionManager, handler);
    }
}
