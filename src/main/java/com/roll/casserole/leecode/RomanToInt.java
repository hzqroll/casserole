package com.roll.casserole.leecode;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * <p>
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * <p>
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * created on 2020/3/31 3:07 下午
 */


public class RomanToInt {
    public int romanToInt(String s) {
        int result = 0;
        char[] strings = s.toCharArray();
        if (strings.length == 1) {
            return getValue(strings[0]);
        }
        for (int index = 0; index < strings.length; index++) {
            int i = getValue(strings[index]);

            int result1 = 0;
            if (index != strings.length - 1) {
                int i1 = getValue(strings[index + 1]);
                //通常情况下，罗马数字中小的数字在大的数字的右边
                // VI
                if (i >= i1) {
                    result1 = i;
                    // IV
                } else {
                    result1 = i1 - i;
                    index = index + 1;
                }
            } else {
                result1 = i;
            }
            result = result1 + result;
        }
        return result;
    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("123".toCharArray());
    }
}
