package com.roll.casserole.todel;

import java.util.Arrays;

/**
 * @author roll
 * created on 2019-11-26 14:10
 */
public class PTest {
    static String a = "zzbank,ccbz,cmbchina,bhzcf,wzyh,dongguanbank,cscbemd,58jinrong,ncssyyh,qzyh,jnbank,bobcfc,abcvc,newnew";

    public static void main(String[] args) {
        String[] b = a.split(",");
        for (int i = 0; i < b.length; i++) {
            b[i] = "\"" + b[i] + "\"";
        }
        System.out.println(Arrays.toString(b));
    }
}
