package com.roll.casserole.leecode;

import java.util.Arrays;

/**
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * 示例 2：
 * <p>
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 *
 * @author roll
 * created on 2020/4/17 11:50 上午
 */
public class SubtractProductAndSum {
    public static int subtractProductAndSum(int n) {
        int r1 = 0;
        int r2 = 1;
        while (n > 0) {
            int i1 = n % 10;
            n = n / 10;
            r1 = r1 + i1;
            r2 = r2 * i1;
        }
        return r2 - r1;
    }

    public static void main(String[] args) {
        System.out.println(subtractProductAndSum(4421));
    }
}
