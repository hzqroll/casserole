package com.roll.casserole.effective;

/**
 * @author zongqiang.hao
 * created on 2018/10/21 下午3:00.
 */
public class CloneDemos implements Cloneable {
    private String str;

    public static void main(String args[]) {
        String a = "casserole";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
