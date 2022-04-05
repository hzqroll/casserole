package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.player.Player;
import com.roll.casserole.domain.v.v2.weapon.Weapon;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:50 下午
 */
public class CombatServiceImpl implements CombatService {

    private DamageManager damageManager;

    @Override
    public void performAttack(Player player, Monster monster) {
        Weapon weapon = null;//weaponRepository.find(player.getWeaponId());
        int damage = damageManager.calculateDamage(player, weapon, monster);
        if (damage > 0) {
            monster.takeDamage(damage); // （Note 1）在领域服务里变更Monster
        }
        // 省略掉Player和Weapon可能受到的影响
    }
}
