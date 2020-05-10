package com.roll.casserole.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author roll
 * created on 2020/5/9 4:39 下午
 */
public class SubarraysWithKDistinct {
    public static int subarraysWithKDistinct(int[] A, int K) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            Map<Integer, Integer> iMap = new HashMap<>();
            iMap.put(A[i], i);
            if (i == A.length - 1) {
                if (iMap.size() == K) {
                    result = result + 1;
                }
            } else {
                for (int j = i + 1; j < A.length; j++) {
                    if (iMap.size() == K) {
                        result = result + 1;
                    }
                    iMap.put(A[j], j);
                    if (j == A.length - 1) {
                        if (iMap.size() == K) {
                            result = result + 1;
                        }
                    }
                    if (iMap.size() > K) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        int k = 1;
        System.out.println(subarraysWithKDistinct(nums, k));
    }
}
