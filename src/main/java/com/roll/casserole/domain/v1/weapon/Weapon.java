package com.roll.casserole.domain.v1.weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:43 上午
 */
public abstract class Weapon {
    String name;
    int damage;
    int damageType; // 0 - physical, 1 - fire, 2 - ice etc.

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamageType() {
        return damageType;
    }

    public void setDamageType(int damageType) {
        this.damageType = damageType;
    }
}
