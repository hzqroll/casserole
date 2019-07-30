package com.roll.casserole.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author roll
 * created on 2019-07-30 21:18
 */
public class Solution1 {


    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int other = target - nums[i];
            // 一边循环，一边找，找不到的放入到map里面，而且要从map里面拿到下标，所以找到第一个符合的，但是map里面找不到，所以要继续找，空间O(n),时间O(n)
            if (numsMap.containsKey(other) && numsMap.get(other) != i) {
                result[1] = i;
                result[0] = numsMap.get(other);
                return result;
            } else {
                numsMap.put(nums[i], i);
            }
        }

        return result;
    }

    /*public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            // 有这个key， 并且不能喝当前key对应的下标相等。
            if (numsMap.containsKey(other) && numsMap.get(other) != i) {
                result[0] = i;
                result[1] = numsMap.get(other);
                return result;
            }
        }

        return result;
    }*/

    /*public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }*/

    public static void main(String[] args) {
        int[] a = new int[]{3, 3, 4};

        int target = 6;
        int[] b = twoSum(a, target);
        System.out.println(Arrays.toString(b));

    }
}
