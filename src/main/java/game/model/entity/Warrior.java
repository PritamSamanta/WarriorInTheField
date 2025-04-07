package main.java.game.model.entity;

import main.java.game.constants.GameConstants;

public class Warrior extends Character {
    private boolean blocking;
    private boolean invisible;
    private int blockPercentage;

    public Warrior(int health) {
        super(health);
        blocking = false;
        invisible = false;
        blockPercentage = 0;
    }

    public void setBlocking(boolean blocking, int blockPercentage) {
        this.blocking = blocking;
        this.blockPercentage = blockPercentage;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public int getBlockPercentage() {
        return blockPercentage;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void heal(int amount) {
        health += amount;
        if (health > GameConstants.MAX_WARRIOR_HEALTH) {
            health = GameConstants.MAX_WARRIOR_HEALTH;
        }
    }

    public void updateStatusEffects() {
        // Reset statuses at the end of turn
        blocking = false;
        invisible = false;
    }
}
