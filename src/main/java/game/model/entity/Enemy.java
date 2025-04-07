package main.java.game.model.entity;

import main.java.game.constants.GameConstants;
import main.java.game.util.RandomGenerator;

public class Enemy extends Character {
    private int enemyNumber;
    private int frozenTurns;

    public Enemy(int enemyNumber) {
        super(RandomGenerator.getRandomInt(GameConstants.MIN_ENEMY_HEALTH, GameConstants.MAX_ENEMY_HEALTH));
        this.enemyNumber = enemyNumber;
        this.frozenTurns = 0;
    }

    public boolean isFrozen() {
        return frozenTurns > 0;
    }

    public void freeze(int turns) {
        frozenTurns = turns;
    }

    public void updateStatusEffects() {
        if (frozenTurns > 0) {
            frozenTurns--;
        }
    }

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public int getFrozenTurns() {
        return frozenTurns;
    }
}
