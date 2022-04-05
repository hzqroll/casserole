package com.roll.casserole.domain.v.v2.player;

import com.roll.casserole.domain.v.v2.Movable;
import com.roll.casserole.domain.v.v2.PlayerClass;
import com.roll.casserole.domain.v.v2.Transform;
import com.roll.casserole.domain.v.v2.Vector;
import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.weapon.Weapon;
import com.roll.casserole.domain.v.v2.weapon.WeaponId;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:42 上午
 */
public class Player implements Movable {
    private PlayerId id;
    private String name;
    private PlayerClass playerClass; // enum
    private WeaponId weaponId; // （Note 1）
    private Transform position = Transform.ORIGIN;
    private Vector velocity = Vector.ZERO;

    @Override
    public Transform getPosition() {
        return position;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    public void moveTo(long x, long y) {
        this.position = new Transform(x, y);
    }

    public void startMove(long velocityX, long velocityY) {
        this.velocity = new Vector(velocityX, velocityY);
    }

    public void stopMove() {
        this.velocity = Vector.ZERO;
    }

    @Override
    public boolean isMoving() {
        return this.velocity.getX() != 0 || this.velocity.getY() != 0;
    }

    public PlayerId getId() {
        return id;
    }

    public void setId(PlayerId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public WeaponId getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(WeaponId weaponId) {
        this.weaponId = weaponId;
    }

    public void setPosition(Transform position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
