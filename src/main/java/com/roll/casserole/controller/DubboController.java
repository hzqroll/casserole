package com.roll.casserole.controller;

import com.roll.casserole.dubbo.client.DemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午3:53.
 */
@RestController
@RequestMapping("/demo")
public class DubboController {

    @Autowired
    private DemoClient demoClient;

    @RequestMapping("print")
    public String getPrint(String name, @Context HttpServletRequest request) {
        System.out.print(request.getHeaderNames());
        return demoClient.print(name);
    }
}
