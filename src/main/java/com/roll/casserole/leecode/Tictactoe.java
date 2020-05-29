package com.roll.casserole.leecode;

import java.util.Arrays;

/**
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * <p>
 * 示例 1：
 * <p>
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 * <p>
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 * <p>
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/tic-tac-toe-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author roll
 * created on 2020/5/28 5:25 下午
 */
public class Tictactoe {
    public static String tictactoe(String[] board) {
        String[] resultStringArray = new String[board.length];
        String[][] a = new String[board.length][board.length];
        int i = 0;

        boolean x = false;
        boolean o = false;

        for (String b : board) {
            char[] c = b.toCharArray();
            boolean flag = true;
            for (int j = 0; j < c.length; j++) {
                if (j == 0) {
                    resultStringArray[j] = String.valueOf(c[j]);
                }
                if (flag) {
                    if (j == 0) {
                        if (String.valueOf(c[j]).equals("X") || String.valueOf(c[j]).equals("O")) {
                            flag = true;
                        }
                    } else if (j == c.length - 1) {
                        if (c[j] != c[j - 1]) {
                            flag = false;
                        }
                        if (String.valueOf(c[j]).equals("X") && flag) {
                            x = true;
                        }
                        if (String.valueOf(c[j]).equals("O") && flag) {
                            o = true;
                        }
                    } else {
                        if (c[j] != c[j - 1]) {
                            flag = false;
                        }
                    }
                }

                a[i][j] = String.valueOf(c[j]);
            }
            i++;
        }
        System.out.println(Arrays.deepToString(a));

        boolean bevel = true;
        for (int i1 = 1; i1 < board.length; i1++) {
            for (int j1 = 0; j1 < board.length; j1++) {
                // 和上一个相等，赋值上一个值，否则是" "
                if (a[i1][j1].equals(resultStringArray[j1])) {
                    resultStringArray[j1] = a[i1][j1];
                } else {
                    resultStringArray[j1] = " ";
                }

                // 判断斜的是否相等
                if ((i1 == j1) && bevel) {

                }
            }
        }
        // 判断结果，如果存在X，X获胜，如果存在O，O获胜，如果都存在，平局
        for (String s : resultStringArray) {
            if (s.equals("X")) {
                x = true;
            }
            if (s.equals("O")) {
                o = true;
            }
        }
        // 二次结果判断
        if (x && o) {
            return "Pending";
        } else if (x) {
            return "X";
        } else if (o) {
            return "O";
        } else {
            return "Draw";
        }
    }

    public static void main(String[] args) {
        //String[] board = new String[]{" O    X", " O     ", "     O ", "XXXXXXX", "  O    ", " O   O ", "O  X OO"};
        String[] board = new String[]{"O X", " XO", "X O"};
        System.out.println(tictactoe(board));
    }
}
