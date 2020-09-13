package com.roll.casserole.leecode;

import okhttp3.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author roll
 * created on 2020-01-31 10:56
 */
public class Solution5 {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] tem = s.toCharArray();
        int start = 0;
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > max && isPalindrome(tem, i, j)) {
                    max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + max);
    }

    // 判断是否是回文串
    public static boolean isPalindrome(char[] array, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            } else {
                i = i + 1;
                j = j - 1;
            }
        }
        return true;
    }

    public static String longestPalindromeWithDp(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串

        // 2. 初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String s = "abacabs";
        char[] tem = s.toCharArray();
        System.out.println(longestPalindromeWithDp(s));
    }
}
