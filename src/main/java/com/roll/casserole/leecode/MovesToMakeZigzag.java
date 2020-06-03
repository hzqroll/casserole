package com.roll.casserole.leecode;

/**
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * <p>
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * <p>
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * <p>
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decrease-elements-to-make-array-zigzag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author roll
 * created on 2020/6/3 3:33 下午
 */
public class MovesToMakeZigzag {
    public static int movesToMakeZigzag(int[] nums) {
        int length = nums.length;
        int[] a = nums.clone();
        int count = 0;
        // 偶数的大
        for (int i = 0; i < length; i++) {
            // 找到偶数位置
            if (i % 2 == 0) {
                if (i != 0) {
                    if (nums[i] <= nums[i - 1]) {
                        count = count + nums[i - 1] - nums[i] + 1;
                    }
                }
                if (i != length - 1) {
                    if (nums[i] <= nums[i + 1]) {
                        count = count + nums[i + 1] - nums[i] + 1;
                        nums[i + 1] = nums[i] - 1;
                    }
                }
            }
        }

        int count1 = 0;
        // 奇数的大
        for (int i = 0; i < length; i++) {
            // 找到奇数位置
            if (i % 2 == 1) {
                if (i != length - 1) {
                    if (a[i] <= a[i + 1]) {
                        count1 = count1 + a[i + 1] - a[i] + 1;
                        a[i + 1] = a[i] - 1;
                    }
                }
                if (a[i] <= a[i - 1]) {
                    count1 = count1 + a[i - 1] - a[i] + 1;
                }
            }
        }
        return Math.min(count, count1);
    }

    public static void main(String[] args) {
        int[] a = new int[]{
                7, 4, 8, 9, 7, 7, 5};
        System.out.println(movesToMakeZigzag(a));
    }
}
