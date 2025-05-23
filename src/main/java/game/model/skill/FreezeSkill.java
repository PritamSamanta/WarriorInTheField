package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;

public class FreezeSkill extends AttackSkill {
    public FreezeSkill() {
        super("Freeze", GameConstants.FREEZE_INPUT, GameConstants.ACTION_COOLDOWN_TURNS);
    }

    @Override
    public int execute(Warrior warrior, Enemy enemy) {
        enemy.freeze(GameConstants.FREEZE_DURATION);
        triggerCooldown();
        return 0;
    }
}
