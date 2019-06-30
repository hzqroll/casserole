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
        //System.out.println(MD5.encode("342401199203039415"));

        /*double  num = 55.3;
        int i = (int) num;
        System.out.println(i);

        double ta = (num - i) * 100;
        System.out.println(ta);

        int tb = (int) ta;
        System.out.println(tb);*/

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10 ,TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        List<Integer> ids = new ArrayList<>();
        //在这里分批
        List<Integer> needDealIds = ids.subList(0,20);
        threadPoolExecutor.execute(new Test(needDealIds));

    }


    @Override
    public void run() {
        System.out.println("你的任务！ 处理你的id"+ ids.toString());
    }
}
