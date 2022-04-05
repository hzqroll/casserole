package com.roll.casserole.domain.v.v2;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 4:19 下午
 */
public interface Movable {
    // 相当于组件
    Transform getPosition();
    Vector getVelocity();

    // 行为
    void moveTo(long x, long y);
    void startMove(long velX, long velY);
    void stopMove();
    boolean isMoving();
}
