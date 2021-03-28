package com.roll.casserole.leecode;

import java.util.Arrays;

/**
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * <p>
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * <p>
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
 * <p>
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
 * A 或 B 中的元素在原字符串中可以不连续。
 * A.length + B.length = seq.length
 * max(depth(A), depth(B)) 的可能取值最小。
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
 * <p>
 * answer[i] = 0，seq[i] 分给 A 。
 * answer[i] = 1，seq[i] 分给 B 。
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 * <p>
 * 示例 1：
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 * 示例 2：
 * <p>
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= text.size <= 10000
 *  
 * <p>
 * 有效括号字符串：
 * <p>
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
 * 下述几种情况同样属于有效括号字符串：
 * <p>
 * 1. 空字符串
 * 2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 * 3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * 嵌套深度：
 * <p>
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 * 1. s 为空时，depth("") = 0
 * 2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 * 3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 *
 * @author roll
 * created on 2020/4/1 1:53 下午
 * 知道如何计算嵌套深度，问题就很简单了：只要在遍历过程中，我们保证栈内一半的括号属于序列 A，一半的括号属于序列 B，
 * 那么就能保证拆分后最大的嵌套深度最小，是当前最大嵌套深度的一半。要实现这样的对半分配，我们只需要把奇数层的
 * ( 分配给 A，偶数层的 ( 分配给 B 即可。对于上面的例子，我们将嵌套深度为 1 和 3 的所有括号 (()) 分配给 A，
 * 嵌套深度为 2 的所有括号 ()()() 分配给 B。
 * <p>
 * 此外，由于在这个问题中，栈中只会存放 (，因此我们不需要维护一个真正的栈，只需要用一个变量模拟记录栈的大小。
 * <p>
 *     https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/
 */
public class MaxDepthAfterSplit {
    public static int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int[] s = new int[chars.length];
        int index = 0;
        int flag = 1;
        for (char aChar : chars) {
            if (String.valueOf(aChar).equals("(")) {
                // 深度+1
                flag = flag + 1;
                // 偶数/奇数 判断
                s[index] = flag % 2;
            } else if (String.valueOf(aChar).equals(")")) {
                // 偶数/奇数判断
                s[index] = flag % 2;
                // 深度-1
                flag = flag - 1;
            }
            index++;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println("start");
        System.out.println(Arrays.toString(maxDepthAfterSplit("()(())()")));
        char a = 'c';
        System.out.println('c');
        System.out.println(a - '0');
    }

    /**
     * 题面中的 depth 其实就是栈的最大深度。
     * “你需要从中选出任意一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小”。
     * 这句话其实相当于让 A 字符串和 B 字符串的 depth 尽可能的接近。
     * 为什么呢？因为 seq 对应的栈上，每个左括号都对应一个深度，
     * 而这个左括号，要么是 A 的，要么是 B 的。
     * 所以，栈上的左括号只要按奇偶分配给A和B就可以啦！时间复杂度很明显是 O(n) 的，空间复杂度也是 O(n)（如果算返回的变量的话）。
     *
     * 作者：sweetiee
     * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/ti-mian-shuo-ming-ti-mu-jiang-jie-shuo-hao-fa-wan-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public class Solution {
        public int[] maxDepthAfterSplit(String seq) {
            int[] ans = new int[seq.length()];
            int idx = 0;
            for (char c : seq.toCharArray()) {
                ans[idx++] = c == '(' ? idx & 1 : ((idx + 1) & 1);
            }
            return ans;
        }
    }
}
