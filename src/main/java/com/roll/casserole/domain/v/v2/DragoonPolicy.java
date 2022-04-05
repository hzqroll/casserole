package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:52 下午
 */
public class DragoonPolicy implements DamagePolicy{
    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        return weapon.getDamage() * 2;
    }
    @Override
    public boolean canApply(Player player, Weapon weapon, Monster monster) {
        return player.getPlayerClass() == PlayerClass.Dragoon &&
                monster.getMonsterClass() == MonsterClass.Dragon;
    }
}
