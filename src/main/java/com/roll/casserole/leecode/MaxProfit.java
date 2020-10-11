package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>@author roll
 * <p>created on 2020/8/31 2:10 下午
 */
public class MaxProfit {
    public static int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                int conResult = prices[j] - prices[i];
                if (conResult > result) {
                    result = conResult;
                }
            }
        }
        return result;
    }

    public static int maxProfit2(int[] prices) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i : prices) {
            if (i - min > result) {
                result = i - min;
            }
            if (i < min) {
                min = i;
            }
        }
        return result;
    }


    public static int maxProfit1(int[] prices) {
        if (prices == null) {
            return 0;
        }
        if (prices.length == 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        // 找出最大值和最小值，并且最大值在最小值的前面
        for (int price : prices) {
            if (min > price) {
                min = price;
            }
            if (price - min > result) {
                result = price - min;
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> maps = new HashMap<>(nums.length);
        int i = 0;
        for (Integer item : nums) {
            int a = target - item;
            if (maps.containsKey(a)) {
                return new int[]{
                        a, maps.get(a)
                };
            } else {
                maps.put(item, i);
            }
            i++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{
                7, 2, 5, 3, 6, 4, 10, 11, 1
        };
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }

    /**
     * 如果每天都可以买卖
     */
    public static int maxProfit3(int[] prices) {
        int result = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                result = result + prices[i] - min;
            }
            min = prices[i];
        }
        return result;
    }
}
