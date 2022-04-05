package com.roll.casserole.domain.v1.moster;

import com.roll.casserole.domain.v1.Movable;
import com.roll.casserole.domain.v1.player.Player;
import com.roll.casserole.domain.v1.weapon.Gun;
import com.roll.casserole.domain.v1.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:42 上午
 */
public abstract class Monster extends Movable {
    String name;
    Long health;
    public void receiveDamageBy(Weapon weapon, Player player) {
        this.health -= weapon.getDamage(); // 基础规则
        if (weapon instanceof Gun) { // 新的逻辑
            this.setHealth(0L);
        }
    }

    public Monster(String name, Long health) {
        this.name = name;
        this.health = health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getHealth() {
        return health;
    }

    int x;
    int y;
    void move(int targetX, int targetY) {
        // logic
    }
}
