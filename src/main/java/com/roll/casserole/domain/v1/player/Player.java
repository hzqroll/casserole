package com.roll.casserole.domain.v1.player;

import com.roll.casserole.domain.v1.Movable;
import com.roll.casserole.domain.v1.moster.Monster;
import com.roll.casserole.domain.v1.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:42 上午
 */
public abstract class Player extends Movable {
    Weapon weapon;

    String name;

    public Player(String name) {
        this.name = name;
    }

    public void attack(Monster monster) {
        monster.receiveDamageBy(weapon, this);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    int x;
    int y;

    void move(int targetX, int targetY) {
        // logic
    }
}
