package com.roll.casserole.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongqiang.hao
 * created on 2018/9/9 下午9:29.
 */
@RestController
public class TestController {
    public void get(HttpRequest request, HttpHeaders headers){
        headers.get("23");
    }
}
