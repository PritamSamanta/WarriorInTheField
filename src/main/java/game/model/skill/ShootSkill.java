package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;
import main.java.game.util.RandomGenerator;

public class ShootSkill extends AttackSkill {
    public ShootSkill() {
        super("Shoot", GameConstants.SHOOT_INPUT, GameConstants.ACTION_COOLDOWN_TURNS);
    }

    @Override
    public int execute(Warrior warrior, Enemy enemy) {
        int damage = RandomGenerator.getRandomInt(
                GameConstants.MIN_SHOOT_DAMAGE,
                GameConstants.MAX_SHOOT_DAMAGE
        );
        enemy.takeDamage(damage);
        triggerCooldown();
        return damage;
    }
}
