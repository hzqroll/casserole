package com.roll.casserole.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author zongqiang.hao
 * created on 2018/9/9 下午4:34.
 */
public class OKHttpUtils {
    private String url;

    private String REQUEST_METHOD = HTTPMethod.HTTP_GET;

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public OKHttpUtils() {
    }

    public OKHttpUtils(String url) {
        this.url = url;
    }

    public OKHttpUtils(String url, String REQUEST_METHOD) {
        this.url = url;
        this.REQUEST_METHOD = REQUEST_METHOD;
    }

    public Response get(String url) throws IOException {
        this.url = url;
        Request request = new Request.Builder()
                .url(this.url)
                .method(REQUEST_METHOD, null)
                .header("key", "value")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.print(response.isSuccessful());
        return response;
    }

    public static void main(String args[]) throws IOException {
        String url = "https://www.jianshu.com/p/ca8a982a116b";
        OKHttpUtils okHttpUtils = new OKHttpUtils(url);
        okHttpUtils.get(url);
    }
}
