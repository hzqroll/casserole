package com.roll.casserole.leecode;

/**
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 * <p>
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author roll
 * created on 2020/4/22 5:37 下午
 */
public class MinFallingPathSum {
    public static int minFallingPathSum(int[][] A) {
        int[] basic = A[0];
        int result = 10000;

        // 其他位数增加的偏移位
        for (int i1 = 0; i1 < basic.length; i1++) {
            int[] next = new int[3];
            if (i1 == 0) {
                next = new int[]{0, 1};
            } else {
                next[0] = i1 - 1;
                next[1] = i1;
                next[2] = i1 + 1;
            }
            for (int i2 = 0; i2 < next.length; i2++) {
                int result1 = basic[i1];
                for (int i = 1; i < A.length; i++) {
                    if (A[i].length > next[i2]) {
                        result1 = result1 + A[i][next[i2]];
                    } else {
                        result1 = 1000;
                    }
                }
                if (result > result1) {
                    result = result1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{17, 82}, {1, -44}};
        System.out.println(minFallingPathSum(A));
    }
}
