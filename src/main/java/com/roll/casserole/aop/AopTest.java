package com.roll.casserole.aop;

import com.roll.casserole.aop.cglib.CglibInterceptor;
import com.roll.casserole.common.DogTalk;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author zongqiang.hao
 * created on 2019-03-21 08:52.
 */
public class AopTest {
    public static void main(String[] args){
        cglibTest();
    }

    public static void cglibTest(){
        Enhancer enhancer =  new Enhancer();
        enhancer.setSuperclass(DogTalk.class);
        enhancer.setCallback(new CglibInterceptor());
        DogTalk dogTalk = (DogTalk) enhancer.create();
        dogTalk.say();
        dogTalk.getName("2231");
    }
}
