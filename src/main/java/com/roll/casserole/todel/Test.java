package com.roll.casserole.todel;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zongqiang.hao
 * created on 2018/10/11 上午9:04.
 */
public class Test implements Runnable{

    private List<Integer> ids;

    public Test(List<Integer> ids) {
        this.ids = ids;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        System.out.println(10 % 3);
        System.out.println(10 / 3);

    }


    @Override
    public void run() {
        System.out.println("你的任务！ 处理你的id"+ ids.toString());
    }
}
