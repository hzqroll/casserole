package com.roll.casserole.domain.v.v2.moster;

import com.roll.casserole.domain.v.v2.*;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:42 上午
 */
public class Monster implements Movable {
    private MonsterId id;
    private MonsterClass monsterClass; // enum
    private Health health;
    private Transform position = Transform.ORIGIN;
    private Vector velocity = Vector.ZERO;

    @Override
    public Transform getPosition() {
        return null;
    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public void moveTo(long x, long y) {

    }

    @Override
    public void startMove(long velX, long velY) {

    }

    @Override
    public void stopMove() {

    }

    @Override
    public boolean isMoving() {
        return false;
    }

    public MonsterId getId() {
        return id;
    }

    public void setId(MonsterId id) {
        this.id = id;
    }

    public MonsterClass getMonsterClass() {
        return monsterClass;
    }

    public void setMonsterClass(MonsterClass monsterClass) {
        this.monsterClass = monsterClass;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public void setPosition(Transform position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void takeDamage(int damage) {
        this.health.setHealth(this.health.getHealth() - damage);
    }
}
