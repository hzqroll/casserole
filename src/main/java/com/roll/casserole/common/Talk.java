package com.roll.casserole.common;

/**
 * @author zongqiang.hao
 * created on 2019-03-09 20:04.
 */
public interface Talk {
    void say();

    void say(String talk);

    String getName(String name);

    void initUser(User user);
}
