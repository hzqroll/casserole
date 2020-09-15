package com.roll.casserole.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numMap = new HashMap<>(nums.length);
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (numMap.containsKey(a)) {
                result[0] = numMap.get(a);
                result[1] = i;
            } else {
                numMap.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 3};
        //System.out.println(Arrays.toString(twoSum(a, 6)));
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }

    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     *
     * @param amount amount = 5
     * @param coins  coins = [1, 2, 5]
     * @return
     */
    public int change(int amount, int[] coins) {

        return 0;
    }
}
