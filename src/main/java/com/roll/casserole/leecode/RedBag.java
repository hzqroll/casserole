package com.roll.casserole.leecode;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>@author roll
 * <p>created on 2020/9/14 4:48 下午
 */
public class RedBag {
    public static void main(String[] args) {
        int a = 100;
        int b = 10;
        // 1. 每个人都有红包
        // 2。 金额随机
        // 3。 最小1分钱
        // 分割法，最小1分钱，先把数据扩大100倍，10个人，只要获取9个线就可以了
        Random random = new Random();
        int[] point = new int[b - 1];
        int index = 0;
        while (index <= (b - 2)) {
            int ra = random.nextInt(10000);
            point[index] = ra;
            index++;
        }
        System.out.println(Arrays.toString(point));
        System.out.println(a);
        int result = 0;
        for (int i : point) {
            result = result + i;
            System.out.println("随机数" + result);
            System.out.println(result / 100);
        }
        System.out.println((a * 100) - result);
    }
}
