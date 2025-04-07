package main.java.game.model.skill;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.Enemy;
import main.java.game.model.entity.Warrior;
import main.java.game.util.RandomGenerator;

public class BlockSkill extends AttackSkill {
    public BlockSkill() {
        super("Block", GameConstants.BLOCK_INPUT, GameConstants.ACTION_COOLDOWN_TURNS);
    }

    @Override
    public int execute(Warrior warrior, Enemy enemy) {
        int blockPercentage = RandomGenerator.getRandomInt(
                GameConstants.MIN_BLOCK_PERCENTAGE,
                GameConstants.MAX_BLOCK_PERCENTAGE
        );
        warrior.setBlocking(true, blockPercentage);
        triggerCooldown();
        return blockPercentage;
    }
}
