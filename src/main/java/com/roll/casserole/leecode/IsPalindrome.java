package com.roll.casserole.leecode;

/**
 * @author roll
 * created on 2020/2/27 10:22 上午
 */
public class IsPalindrome {
    /**
     * 第一次想吧帧数反转过来，对比
     * 第二次：我们将原始数字除以 10，然后给反转后的数字乘上 10，所以，当原始数字小于反转后的数字时，就意味着我们已经处理了一半位数的数字。
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int result = 0;
        while (x != 0 && (x > result)) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (x == 0) {
            return true;
        }
        System.out.println(x);
        System.out.println(result);
        System.out.println(result / x);
        if (result / x >= 10) {
            result = result / 10;
        }
        return (result == x);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(12123));
    }
}
