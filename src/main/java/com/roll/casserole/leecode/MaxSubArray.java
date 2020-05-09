package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题42. 连续子数组的最大和
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * @author roll
 * created on 2020/5/9 9:35 上午
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        for (int l = 1; l < nums.length; l++) {
            int a = nums[l] + nums[l - 1];
            if (nums[l] < a) {
                nums[l] = a;
            }
        }
        int result = nums[0];
        for (Integer c : nums) {
            result = Math.max(c, result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        System.out.println(maxSubArray(nums));
    }
}
