package com.roll.casserole.leecode;

/**
 * @author roll
 * created on 2020/4/23 5:36 下午
 */
public class Fibonacci {
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * dp
     * 状态转移方程：f(n) = f(n-1) + f(n-2)
     */
    public static int fibonacci1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int n1 = 1;
        int n2 = 1;
        int result = 2;
        for (int i = 3; i < n + 1; i++) {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacci1(10));
    }
}
