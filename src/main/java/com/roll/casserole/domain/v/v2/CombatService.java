package com.roll.casserole.domain.v.v2;

import com.roll.casserole.domain.v.v2.moster.Monster;
import com.roll.casserole.domain.v.v2.player.Player;

/**
 * 战斗系统
 * <p>@author zongqiang
 * <p>created on 2021/6/9 7:50 下午
 */
public interface CombatService {
    void performAttack(Player player, Monster monster);
}
