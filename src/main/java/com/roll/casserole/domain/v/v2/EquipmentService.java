package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:36 下午
 */
public interface EquipmentService {
    /**
     * player是否可以装配weapon
     * @param player
     * @param weapon
     * @return
     */
    boolean canEquip(Player player, Weapon weapon);
}
