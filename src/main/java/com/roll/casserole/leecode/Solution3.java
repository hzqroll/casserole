package com.roll.casserole.leecode;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
        int maxLength = 0;

        // 转换后的list
        Map<Character, Integer> characterSet = new HashMap<>();
        int i = 0;
        if (s.length() == 1) {
            return 1;
        }
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            if (characterSet.containsKey(currentChar)) {
                if (characterSet.size() > maxLength) {
                    maxLength = characterSet.size();
                }
                i = characterSet.get(currentChar) + 1;
                characterSet.clear();
            } else {
                characterSet.put(currentChar, i);
                i = i + 1;
                if (characterSet.size() > maxLength) {
                    maxLength = characterSet.size();
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(""));

        System.out.println(lengthOfLongestSubstring("au"));
    }
}
