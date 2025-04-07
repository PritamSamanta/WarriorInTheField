package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;
import main.java.game.util.RandomGenerator;

public class HealSkill extends AttackSkill {
    public HealSkill() {
        super("Heal", GameConstants.HEAL_INPUT, GameConstants.ACTION_COOLDOWN_TURNS);
    }

    @Override
    public int execute(Warrior warrior, Enemy enemy) {
        int healAmount = RandomGenerator.getRandomInt(
                GameConstants.MIN_HEAL_AMOUNT,
                GameConstants.MAX_HEAL_AMOUNT
        );
        warrior.heal(healAmount);
        triggerCooldown();
        return healAmount;
    }
}
