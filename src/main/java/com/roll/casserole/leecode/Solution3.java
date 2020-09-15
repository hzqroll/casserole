package com.roll.casserole.leecode;

import java.util.*;

/**
 * @author roll
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * created on 2019-12-05 15:41
 */
public class Solution3 {
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        // 保存最大长度的数据，每次碰到重复的清空内容
        List<Character> cList = new ArrayList<>(s.length());

        // 开始计算位置
        for (int i = 0; i < s.length(); i++) {
            if (!cList.contains(s.charAt(i))) {
                cList.add(s.charAt(i));
            } else {
                if (cList.contains(s.charAt(i))) {
                    cList.subList(0, cList.indexOf(s.charAt(i)) + 1).clear();
                }
                i = i - 1;
            }
            if (max < cList.size()) {
                max = cList.size();
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring1(String s) {
        List<Character> cList = new ArrayList<>(s.length());
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!cList.contains(c)) {
                cList.add(c);
            } else {
                if (cList.contains(s.charAt(i))) {
                    cList.subList(0, cList.indexOf(s.charAt(i)) + 1).clear();
                }
                i = i - 1;
            }
            if (maxLength < cList.size()) {
                maxLength = cList.size();
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));

        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
        System.out.println(lengthOfLongestSubstring1("aabaab!bb"));

    }

    private static Solution3 instance = null;

    private static final Object lock = new Object();

    public static Solution3 getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance != null) {
                    return instance;
                }
                instance = new Solution3();
            }
        }
        return instance;
    }
}
