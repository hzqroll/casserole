package com.roll.casserole.algorithm;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * <p>@author zongqiang
 * <p>created on 2022/4/5 12:53 下午
 */
public class Red {
    private volatile int total;
    private int num;
    private int min;
    private int sended;
    private int sendedTotal;

    public Red(int total, int num, int min) {
        this.total = total;
        this.num = num;
        this.min = min;
        this.sended = 0;
        this.sendedTotal = 0;
    }

    public double openRedBucket(int maxi) {
        if (sended + 1 == num) {
            return total;
        }
        if (sended == num) {
            throw new RuntimeException("没钱了");
        }
        if (sended == 0 && total >= 1) {
            sended++;
            sendedTotal = sendedTotal + 1;
            return 0.1;
        }
        sended++;
        return getRandomBoundsWithBound(total, num, sendedTotal, sended, min, maxi);
    }

    private synchronized void discount(int money) {
        if (money <= total) {
            this.total = total - money;
        }
    }

    public int getRandomVal(int mini, int maxi) {
        Random random = new Random();
        return random.nextInt(maxi - mini + 1) + mini;
    }

    public int getRandomBoundsWithBound(int total, int num, int sended, int sendedNum, int min, int max) {
        min = Math.max((total - sended - (num - sendedNum) * max), min);
        max = Math.min((total - sended - (num - sendedNum) * min), max);
        int sendedMoney = getRandomVal(min, max);
        sendedTotal = sendedTotal + sendedMoney;
        return sendedMoney;
    }

    public static void main(String[] args) {
        Red red = new Red(100, 20, 1);
        for (int i = 10; i < 20; i++) {
            System.out.println(red.openRedBucket(i));
        }
    }
}
