package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>@author roll
 * <p>created on 2020/10/13 4:48 下午
 */
public class LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> numMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numMap.put(nums[i], i);
        }

        int longest = 0;

        for (int num : nums) {
            if (!numMap.containsKey(num - 1)) {
                int currentNum = num;
                int currentLength = 1;
                // 找出大于当前的数据
                while (numMap.containsKey(currentNum + 1)) {
                    currentNum = currentNum + 1;
                    currentLength = currentLength + 1;
                }
                longest = Math.max(currentLength, longest);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
