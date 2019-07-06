package com.roll.casserole.dubbo.server;

import com.roll.casserole.dubbo.CasseroleService;
import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午3:40.
 */
@Service
public class CasseroleServiceImpl implements CasseroleService {
    @Override
    public String getGreet(String name) {
        return "hi" + name;
    }
}
