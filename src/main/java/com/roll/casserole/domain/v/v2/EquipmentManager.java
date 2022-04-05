package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * 策略优先管理
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:44 下午
 */
public class EquipmentManager {
    private static final List<EquipmentPolicy> POLICIES = new ArrayList<>();

    static {
        POLICIES.add(new FighterEquipmentPolicy());
        //POLICIES.add(new MageEquipmentPolicy());
        //POLICIES.add(new DragoonEquipmentPolicy());
        //POLICIES.add(new DefaultEquipmentPolicy());
    }

    public boolean canEquip(Player player, Weapon weapon) {
        for (EquipmentPolicy policy : POLICIES) {
            if (!policy.canApply(player, weapon)) {
                continue;
            }
            return policy.canEquip(player, weapon);
        }
        return false;
    }
}
