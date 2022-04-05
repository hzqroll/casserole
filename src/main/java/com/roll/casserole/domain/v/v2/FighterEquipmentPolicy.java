package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;
import com.roll.casserole.domain.v.v2.weapon.WeaponType;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:44 下午
 */
public class FighterEquipmentPolicy implements EquipmentPolicy{
    @Override
    public boolean canApply(Player player, Weapon weapon) {
        return player.getPlayerClass() == PlayerClass.Fighter;
    }

    /**
     * Fighter能装备Sword和Dagger
     */
    @Override
    public boolean canEquip(Player player, Weapon weapon) {
        return weapon.getWeaponType() == WeaponType.Sword
                || weapon.getWeaponType() == WeaponType.Dagger;
    }
}
