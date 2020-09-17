package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[{]]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>@author roll
 * <p>created on 2020/9/16 10:19 下午
 */
public class IsValid {
    public static boolean isValid(String s) {

        Map<Character, Character> dicts = new HashMap<>();
        dicts.put('[', ']');
        dicts.put('{', '}');
        dicts.put('(', ')');

        Stack<Character> characters = new Stack<>();
        if (s.length() % 2 == 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (characters.size() > 0) {
                if (dicts.get(characters.peek()) != null) {
                    if (dicts.get(characters.peek()) == s.charAt(i)) {
                        characters.pop();
                    }else {
                        characters.push(s.charAt(i));
                    }
                } else {
                    characters.push(s.charAt(i));
                }
            } else {
                characters.push(s.charAt(i));
            }
        }
        return characters.size() == 0;
    }

    public static void main(String[] args) {
        System.out.println(isValid("{}[]"));
    }
}
