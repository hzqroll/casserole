package com.roll.casserole.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zongqiang.hao
 * created on 2018/9/9 下午9:29.
 */
@RestController("/casserole")
public class TestController {
    @RequestMapping("/test")
    public String get(HttpRequest request, HttpHeaders headers) {
        headers.get("23");
        return "success";
    }
}
