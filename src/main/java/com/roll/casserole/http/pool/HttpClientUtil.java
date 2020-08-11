package com.roll.casserole.http.pool;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static CloseableHttpClient client;

    private static final Object lock = new Object();

    /**
     * 入参数据转换器
     *
     * @param params 客户入参
     */
    public static List<NameValuePair> convertNameValuePairList(Map<String, String> params) {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        if (params == null) {
            return null;
        }
        for (Map.Entry<String, String> param : params.entrySet()) {
            nameValuePairList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return nameValuePairList;
    }

    public static CloseableHttpClient getDefaultHttpClient(RequestConfig requestConfig, int maxPoolSize) {
        if (client == null) {
            synchronized (lock) {
                if (client != null) {
                    return client;
                }
                client = HttpClients.custom().
                        setConnectionManager(getDefaultPoolingConnectionManager(maxPoolSize)).
                        setDefaultRequestConfig(requestConfig).
                        build();
                return client;
            }
        } else {
            return client;
        }
    }

    public static CloseableHttpClient getDefaultHttpClient(RequestConfig requestConfig) {
        return getDefaultHttpClient(requestConfig, HttpClientConstant.MAX_TOTAL_SIZE);
    }

    public static CloseableHttpClient getDefaultHttpClient(RequestConfig requestConfig, PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, HttpRequestRetryHandler handler) {
        if (client == null) {
            synchronized (lock) {
                if (client != null) {
                    return client;
                }
                client = HttpClients.custom().
                        setConnectionManager(poolingHttpClientConnectionManager).
                        setDefaultRequestConfig(requestConfig).
                        setRetryHandler(handler).
                        build();
                return client;
            }
        } else {
            return client;
        }
    }

    /**
     * 提供默认的{@link PoolingHttpClientConnectionManager}池连接实现
     *
     * @param maxPoolSize 池大小
     */
    public static PoolingHttpClientConnectionManager getDefaultPoolingConnectionManager(int maxPoolSize) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxPoolSize);
        return poolingHttpClientConnectionManager;
    }

    /**
     * 提供默认的{@link RequestConfig}
     */
    public static RequestConfig getDefaultRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(HttpClientConstant.CONNECT_TIME_OUT).build();
    }

}
