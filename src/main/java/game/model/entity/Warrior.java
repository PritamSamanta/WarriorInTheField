package main.java.game.model.entity;

import main.java.game.constants.GameConstants;

public class Warrior extends Character {
    private boolean defending;

    public Warrior(int health) {
        super(health);
        defending = false;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isDefending() {
        return defending;
    }

    public void heal(int amount) {
        health += amount;
        if (health > GameConstants.MAX_WARRIOR_HEALTH) {
            health = GameConstants.MAX_WARRIOR_HEALTH;
        }
    }
}
