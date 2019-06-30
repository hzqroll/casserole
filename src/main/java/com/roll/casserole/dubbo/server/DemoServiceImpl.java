package com.roll.casserole.dubbo.server;

import com.roll.casserole.dubbo.casseroleervice;
import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午3:40.
 */
@Service
public class casseroleerviceImpl implements casseroleervice {
    @Override
    public String getGreet(String name) {
        return "hi" + name;
    }
}
