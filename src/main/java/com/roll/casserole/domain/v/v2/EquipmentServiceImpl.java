package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:43 下午
 */
public class EquipmentServiceImpl implements EquipmentService{
    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return false;
    }
}
