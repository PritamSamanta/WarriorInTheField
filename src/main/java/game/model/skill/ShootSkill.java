package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;

public class ShootSkill extends AttackSkill {
    public ShootSkill() {
        super("Shoot", GameConstants.SHOOT_INPUT, GameConstants.ATTACK_COOLDOWN_TURNS);
    }

    @Override
    public void execute(Warrior warrior, Enemy enemy) {
        enemy.takeDamage(GameConstants.SHOOT_DAMAGE);
        triggerCooldown();
    }
}
