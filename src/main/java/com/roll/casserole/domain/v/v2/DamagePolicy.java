package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:52 下午
 */
public interface DamagePolicy {
    /**
     * 计算伤害
     *
     * @param player
     * @param weapon
     * @param monster
     * @return
     */
    int calculateDamage(Player player, Weapon weapon, Monster monster);

    /**
     * 是否使用此策略
     *
     * @param player
     * @param weapon
     * @param monster
     * @return
     */
    boolean canApply(Player player, Weapon weapon, Monster monster);
}
