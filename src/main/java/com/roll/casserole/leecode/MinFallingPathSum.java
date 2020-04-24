package com.roll.casserole.leecode;

import java.util.Map;

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
 * 7
 * 3 8
 * 8 1(2) 0
 * 2 7(3) 4(3) 4
 * 4 5(4) 2(6) 6(4) 5
 *
 * @author roll
 * created on 2020/4/22 5:37 下午
 */
public class MinFallingPathSum {
    /**
     * int N = A.length;
     *         for (int r = N - 2; r >= 0; --r) {
     *             for (int c = 0; c < N; ++c) {
     *                 // best = min(A[r+1][c-1], A[r+1][c], A[r+1][c+1])
     *                 int best = A[r + 1][c];
     *                 if (c > 0)
     *                     best = Math.min(best, A[r + 1][c - 1]);
     *                 if (c + 1 < N)
     *                     best = Math.min(best, A[r + 1][c + 1]);
     *                 A[r][c] += best;
     *             }
     *         }
     *
     *         int result = Integer.MAX_VALUE;
     *         for (int x : A[0]) {
     *             result = Math.min(result, x);
     *         }
     *         return result;
     *
     *
     *<code>
     *     int len = A.length;
     *         int[][] dp = new int[len][len];
     *         for(int i = 0; i < len; i++){
     *             dp[0][i] = A[0][i];
     *         }
     *         for(int i = 1; i < len; i++){
     *             // 第一列计算
     *             dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + A[i][0];
     *             // 中间列计算
     *             for(int j = 1; j < len-1; j++){
     *                 dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i-1][j+1]) + A[i][j];
     *             }
     *             // 最后一列计算
     *             dp[i][len-1] = Math.min(dp[i-1][len-2], dp[i-1][len-1]) + A[i][len-1];
     *         }
     *         int res = Integer.MAX_VALUE;
     *         // 求最后一行中的最小值
     *         for(int j = 0; j < len; j++){
     *             res = Math.min(res, dp[len-1][j]);
     *         }
     *         return res;
     *</code>
     * @param A
     * @return
     */
    public static int minFallingPathSum(int[][] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = A[0][i];
        }
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + A[i][0];
            for (int j = 1; j < len - 1; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + A[i][j];
            }
            dp[i][len - 1] = Math.min(dp[i - 1][len - 2], dp[i - 1][len - 1]) + A[i][len - 1];
        }
        int res = Integer.MAX_VALUE;
        // 求最后一行中的最小值
        for (int j = 0; j < len; j++) {
            res = Math.min(res, dp[len - 1][j]);
        }
        return res;
    }

    public int getMax() {
        int MAX = 101;
        int[][] D = new int[][]{{17, 82}, {1, -44}};   //存储数字三角形
        int n = 0;              //n表示层数
        int i = 0;
        int j = 0;
        int maxSum = getMaxSum(D, n, i, j);
        return maxSum;
    }

    public int getMaxSum(int[][] D, int n, int i, int j) {
        if (i == n) {
            return D[i][j];
        }
        int x = getMaxSum(D, n, i + 1, j);
        int y = getMaxSum(D, n, i + 1, j + 1);
        return Math.max(x, y) + D[i][j];
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 2,3}, {4, 5,6}, {7, 8,9}};
        System.out.println(minFallingPathSum(A));
    }
}
