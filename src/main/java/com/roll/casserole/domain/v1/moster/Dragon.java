package com.roll.casserole.domain.v1.moster;

import com.roll.casserole.domain.v1.player.Dragoon;
import com.roll.casserole.domain.v1.player.Player;
import com.roll.casserole.domain.v1.weapon.Gun;
import com.roll.casserole.domain.v1.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:44 上午
 */
public class Dragon extends Monster {
    public Dragon(String name, Long health) {
        super(name, health);
    }

    @Override
    public void receiveDamageBy(Weapon weapon, Player player) {
        if (weapon instanceof Gun) { // 新的逻辑
            super.receiveDamageBy(weapon, player);
        }
        if (player instanceof Dragoon) {
            this.setHealth(this.getHealth() - weapon.getDamage() * 2L); // 龙骑伤害规则
        }
        // else no damage, 龙免疫力规则
    }
}
