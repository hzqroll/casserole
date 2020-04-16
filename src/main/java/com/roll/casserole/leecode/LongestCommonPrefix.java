package com.roll.casserole.leecode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 *
 * @author zongqiang.hao
 * created on 2020/4/15 10:07 下午.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String commonPrefix = "";
        // 前一个字符串
        String base = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            if (str.equals("")) {
                return "";
            }
            int length = 1;
            boolean flag = true;
            while (flag) {
                flag = false;
                if (length <= base.length()) {
                    String result = base.substring(0, length);
                    if (str.startsWith(result)) {
                        commonPrefix = result;
                        length = length + 1;
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }
            base = commonPrefix;
        }
        return commonPrefix;
        //return commonPrefix.length() > 0 ? commonPrefix.substring(0, commonPrefix.length() - 1) : commonPrefix;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"a", "a", "c"};

        System.out.println(longestCommonPrefix(a));
    }
}
