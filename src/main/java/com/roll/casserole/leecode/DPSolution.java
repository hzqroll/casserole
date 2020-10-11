package com.roll.casserole.leecode;

/**
 * <p>@author roll
 * <p>created on 2020/10/9 9:35 上午
 */
public class DPSolution {
    /**
     * 三角形，求出从最上面的点到最下面的点的最小值
     *
     * @param num
     * @return
     */
    int solution(int[][] num) {
        int result = 0;
        for (int i = num.length; i >= 0; i--) {
            int r1 = num[i][0];
            for (int j = 0; j <= i; j++) {
                r1 = Math.min(r1, num[i][j]);
            }
            result = result + r1;
        }
        return result;
    }

    /**
     * 凑零钱
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 输入: coins = [1, 2, 5], amount = 11，输出: 3
     * 解释: 11 = 5 + 5 + 1 输入: coins = [2], amount = 3，输出: -1
     */
    public static int solution2(int[] coins, int amount) {
        int result = 0;
        int n = amount;
        for (int i = 0; i < n; i++) {
            result = result + 1;
            if (amount == 0) {
                return result;
            }
            if (amount < 0) {
                return -1;
            }
            int r1 = 0;
            for (int coin : coins) {
                if (coin == amount) {
                    return result;
                }
                r1 = Math.max(r1, coin);
            }
            amount = amount - r1;
        }
        return result;
    }

    public static void main(String[] args) {
        int coins[] = new int[]{11, 2, 5};
        System.out.println(solution2(coins, 3));
    }
}
