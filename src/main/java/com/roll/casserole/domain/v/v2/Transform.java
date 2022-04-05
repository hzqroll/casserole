package com.roll.casserole.domain.v.v2;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:26 下午
 */
public class Transform {
    public static final Transform ORIGIN = new Transform(0, 0);
    long x;
    long y;

    public Transform(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
