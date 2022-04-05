package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:44 下午
 */
public interface EquipmentPolicy {
    boolean canApply(Player player, Weapon weapon);

    /**
     * Fighter能装备Sword和Dagger
     */
    boolean canEquip(Player player, Weapon weapon);
}
