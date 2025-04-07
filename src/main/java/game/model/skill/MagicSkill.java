package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;
import main.java.game.util.RandomGenerator;

public class MagicSkill extends AttackSkill {
    public MagicSkill() {
        super("Magic", GameConstants.MAGIC_INPUT, GameConstants.ATTACK_COOLDOWN_TURNS);
    }

    @Override
    public void execute(Warrior warrior, Enemy enemy) {
        int damage = RandomGenerator.getRandomInt(1, 99);
        enemy.takeDamage(damage);
        triggerCooldown();
    }
}
