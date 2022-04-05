package com.roll.casserole.domain.v1;

import com.roll.casserole.domain.v1.moster.Dragon;
import com.roll.casserole.domain.v1.moster.Orc;
import com.roll.casserole.domain.v1.player.Dragoon;
import com.roll.casserole.domain.v1.player.Fighter;
import com.roll.casserole.domain.v1.player.Mage;
import com.roll.casserole.domain.v1.weapon.Staff;
import com.roll.casserole.domain.v1.weapon.Sword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * <p>@author zongqiang
 * <p>created on 2021/6/9 9:48 上午
 */
public class BattleTest {
    @Test
    @DisplayName("Dragon is immune to attacks")
    public void testDragonImmunity() {
        // Given
        Fighter fighter = new Fighter("Hero");
        Sword sword = new Sword("Excalibur", 10);
        fighter.setWeapon(sword);
        Dragon dragon = new Dragon("Dragon", 100L);

        // When
        fighter.attack(dragon);

        // Then
        assertThat(dragon.getHealth()).isEqualTo(100);
    }

    @Test
    @DisplayName("Dragoon attack dragon doubles damage")
    public void testDragoonSpecial() {
        // Given
        Dragoon dragoon = new Dragoon("Dragoon");
        Sword sword = new Sword("Excalibur", 10);
        dragoon.setWeapon(sword);
        Dragon dragon = new Dragon("Dragon", 100L);

        // When
        dragoon.attack(dragon);

        // Then
        assertThat(dragon.getHealth()).isEqualTo(100 - 10 * 2);
    }

    @Test
    @DisplayName("Orc should receive half damage from physical weapons")
    public void testFighterOrc() {
        // Given
        Fighter fighter = new Fighter("Hero");
        Sword sword = new Sword("Excalibur", 10);
        fighter.setWeapon(sword);
        Orc orc = new Orc("Orc", 100L);

        // When
        fighter.attack(orc);

        // Then
        assertThat(orc.getHealth()).isEqualTo(100 - 10 / 2);
    }

    @Test
    @DisplayName("Orc receive full damage from magic attacks")
    public void testMageOrc() {
        // Given
        Mage mage = new Mage("Mage");
        Staff staff = new Staff("Fire Staff", 10);
        mage.setWeapon(staff);
        Orc orc = new Orc("Orc", 100L);

        // When
        mage.attack(orc);

        // Then
        assertThat(orc.getHealth()).isEqualTo(100 - 10);
    }
}
