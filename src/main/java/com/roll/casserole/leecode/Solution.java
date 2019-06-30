package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 下午5:22.
 */
public class Solution {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * @param nums   nums = [2, 7, 11, 15]
     * @param target target = 9
     * @return return [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    if (i != j) {
                        return new int[]{i, j};
                    }
                }
            }
        }*/

        Map<Integer, Integer> cacheMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cacheMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (cacheMap.containsKey(otherNum) && (cacheMap.get(otherNum) != i)) {
                return new int[]{i, cacheMap.get(otherNum)};
            }
        }

        throw new IllegalArgumentException("no solution");
    }

    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     * @param amount amount = 5
     * @param coins coins = [1, 2, 5]
     * @return
     */
    public int change(int amount, int[] coins) {

        return 0;
    }
}
