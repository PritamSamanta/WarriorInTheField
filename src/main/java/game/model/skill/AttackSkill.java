package main.java.game.model.skill;

import main.java.game.model.entity.*;

public abstract class AttackSkill {
    protected String name;
    protected char inputKey;
    protected int cooldown;
    protected int maxCooldown;

    public AttackSkill(String name, char inputKey, int maxCooldown) {
        this.name = name;
        this.inputKey = inputKey;
        this.maxCooldown = maxCooldown;
        this.cooldown = 0;
    }

    public abstract void execute(Warrior warrior, Enemy enemy);

    public boolean isAvailable() {
        return cooldown == 0;
    }

    public void triggerCooldown() {
        cooldown = maxCooldown + 1; // +1 to include current turn
    }

    public void reduceCooldown() {
        if (cooldown > 0) {
            cooldown--;
        }
    }

    public String getName() {
        return name;
    }

    public char getInputKey() {
        return inputKey;
    }

    public int getCooldown() {
        return cooldown;
    }
}

