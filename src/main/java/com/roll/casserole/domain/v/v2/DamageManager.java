package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:51 下午
 */
public class DamageManager {
    private static final List<DamagePolicy> POLICIES = new ArrayList<>();
    static {
        POLICIES.add(new DragoonPolicy());
        //POLICIES.add(new DragonImmunityPolicy());
        //POLICIES.add(new OrcResistancePolicy());
        //POLICIES.add(new ElfResistancePolicy());
        //POLICIES.add(new PhysicalDamagePolicy());
        //POLICIES.add(new DefaultDamagePolicy());
    }

    public int calculateDamage(Player player, Weapon weapon, Monster monster) {
        for (DamagePolicy policy : POLICIES) {
            if (!policy.canApply(player, weapon, monster)) {
                continue;
            }
            return policy.calculateDamage(player, weapon, monster);
        }
        return 0;
    }
}
