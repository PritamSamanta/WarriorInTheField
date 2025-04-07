package main.java.game.model;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.*;
import main.java.game.model.skill.*;

public class GameModel {
    private Warrior warrior;
    private Enemy currentEnemy;
    private int enemiesKilled;
    private int currentEnemyNumber;
    private int timeSlot;
    private SkillManager skillManager;

    public GameModel() {
        warrior = new Warrior(GameConstants.INITIAL_WARRIOR_HEALTH);
        currentEnemyNumber = 1;
        enemiesKilled = 0;
        timeSlot = 1;

        // Initialize skills
        skillManager = new SkillManager();
        skillManager.addSkill(new FreezeSkill());
        skillManager.addSkill(new ShootSkill());
        skillManager.addSkill(new MagicSkill());

        // Spawn first enemy
        spawnNewEnemy();
    }

    public void spawnNewEnemy() {
        currentEnemy = new Enemy(currentEnemyNumber);
    }

    public boolean isGameOver() {
        return warrior.getHealth() <= 0;
    }

    public boolean isEnemyDefeated() {
        return currentEnemy.getHealth() <= 0;
    }

    public void handleEnemyDefeat() {
        enemiesKilled++;
        warrior.heal(GameConstants.HEALTH_BOOST_AFTER_KILL);
        currentEnemyNumber++;
        spawnNewEnemy();
    }

    public void processRadiationDamage() {
        if (timeSlot % GameConstants.RADIATION_INTERVAL == 0) {
            warrior.takeDamage(GameConstants.RADIATION_DAMAGE);
        }
    }

    public void processEnemyAttack() {
        if (!currentEnemy.isFrozen() && !warrior.isDefending()) {
            warrior.takeDamage(GameConstants.ENEMY_ATTACK_DAMAGE);
        }
    }

    public void executeAttack(char attackType) {
        AttackSkill skill = skillManager.getSkill(attackType);
        if (skill != null) {
            skill.execute(warrior, currentEnemy);
        }
    }

    public void increaseTimeSlot() {
        timeSlot++;

        // Update status effects
        currentEnemy.updateStatusEffects();

        // Update cooldowns
        skillManager.updateCooldowns();

        // Reset warrior's defending status
        warrior.setDefending(false);
    }

    // Getters and setters
    public Warrior getWarrior() {
        return warrior;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getCurrentEnemyNumber() {
        return currentEnemyNumber;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }
}