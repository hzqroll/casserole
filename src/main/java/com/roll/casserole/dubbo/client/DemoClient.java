package com.roll.casserole.dubbo.client;

import com.roll.casserole.dubbo.CasseroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午3:41.
 */
@Path("/demo")
@Service
public class DemoClient {

    @Autowired
    private CasseroleService casseroleService;

    @GET
    @Path("/get-print")
    public String print(String name) {
        return casseroleService.getGreet(name);
    }
}
