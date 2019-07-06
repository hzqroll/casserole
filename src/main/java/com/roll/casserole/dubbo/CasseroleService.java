package com.roll.casserole.dubbo;

/**
 * @author zongqiang.hao
 * created on 2018/9/11 下午3:20.
 */
public interface CasseroleService {
    /**
     * 获取信息
     *
     * @param name name
     * @return return hi
     */
    public String getGreet(String name);
}
