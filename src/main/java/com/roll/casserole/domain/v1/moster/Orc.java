package com.roll.casserole.domain.v1.moster;

import com.roll.casserole.domain.v1.player.Player;
import com.roll.casserole.domain.v1.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:43 上午
 */
public class Orc extends Monster {
    public Orc(String name, Long health) {
        super(name, health);
    }

    @Override
    public void receiveDamageBy(Weapon weapon, Player player) {
        if (weapon.getDamageType() == 0) {
            this.setHealth(this.getHealth() - weapon.getDamage() / 2); // Orc的物理防御规则
        } else {
            super.receiveDamageBy(weapon, player);
        }
    }
}
