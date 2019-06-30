package com.roll.casserole.common;

import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2019-03-09 20:05.
 */
@Service(value = "dogTalk")
public class DogTalk implements Talk {
    @Override
    public void say() {
        System.out.println("defalut say: wowo.");
    }

    @Override
    public void say(String talk) {
        System.out.println("say: " + talk);
    }

    @Override
    public String getName(String name) {
        System.out.println(name);
        return name;
    }

    @Override
    public void initUser(User user) {
        System.out.println(user.toString());
    }
}
