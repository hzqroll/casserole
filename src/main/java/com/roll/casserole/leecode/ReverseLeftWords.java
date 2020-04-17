package com.roll.casserole.leecode;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 * <p>
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= k < s.length <= 10000
 *
 * @author roll
 * created on 2020/4/17 11:06 上午
 */
public class ReverseLeftWords {
    public static String reverseLeftWords(String s, int n) {
        String a1 = s.substring(0, n);
        String a2 = s.substring(n);
        return a2.concat(a1);
    }

    public static void main(String[] args) {
        System.out.println(reverseLeftWords("lrloseumgh", 6));
    }

}
