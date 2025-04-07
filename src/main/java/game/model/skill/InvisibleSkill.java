package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;

public class InvisibleSkill extends AttackSkill {
    public InvisibleSkill() {
        super("Invisible", GameConstants.INVISIBLE_INPUT, GameConstants.ACTION_COOLDOWN_TURNS);
    }

    @Override
    public int execute(Warrior warrior, Enemy enemy) {
        warrior.setInvisible(true);
        triggerCooldown();
        return 0;
    }
}
