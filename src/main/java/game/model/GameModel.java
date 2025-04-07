package main.java.game.model;

import main.java.game.constants.GameConstants;
import main.java.game.model.entity.*;
import main.java.game.model.skill.*;
import main.java.game.util.RandomGenerator;

public class GameModel {
    private Warrior warrior;
    private Enemy currentEnemy;
    private int enemiesKilled;
    private int currentEnemyNumber;
    private int timeSlot;
    private SkillManager skillManager;
    private int radiationInterval;

    public GameModel() {
        warrior = new Warrior(GameConstants.INITIAL_WARRIOR_HEALTH);
        currentEnemyNumber = 1;
        enemiesKilled = 0;
        timeSlot = 1;

        // Generate random radiation interval
        radiationInterval = RandomGenerator.getRandomInt(
                GameConstants.MIN_RADIATION_INTERVAL,
                GameConstants.MAX_RADIATION_INTERVAL
        );

        // Initialize skills
        skillManager = new SkillManager();
        skillManager.addSkill(new FreezeSkill());
        skillManager.addSkill(new ShootSkill());
        skillManager.addSkill(new MagicSkill());
        skillManager.addSkill(new BlockSkill());
        skillManager.addSkill(new HealSkill());
        skillManager.addSkill(new InvisibleSkill());

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
        currentEnemyNumber++;
        spawnNewEnemy();

        // Generate new radiation interval
        radiationInterval = RandomGenerator.getRandomInt(
                GameConstants.MIN_RADIATION_INTERVAL,
                GameConstants.MAX_RADIATION_INTERVAL
        );
    }

    public int processRadiationDamage() {
        if (timeSlot % radiationInterval == 0) {
            int damage = RandomGenerator.getRandomInt(
                    GameConstants.MIN_RADIATION_DAMAGE,
                    GameConstants.MAX_RADIATION_DAMAGE
            );
            warrior.takeDamage(damage);
            return damage;
        }
        return 0;
    }

    public int[] processEnemyAttack() {
        if (!currentEnemy.isFrozen() && !warrior.isInvisible()) {
            int damage = RandomGenerator.getRandomInt(
                    GameConstants.MIN_ENEMY_ATTACK_DAMAGE,
                    GameConstants.MAX_ENEMY_ATTACK_DAMAGE
            );

            if (warrior.isBlocking()) {
                int blockPercentage = warrior.getBlockPercentage();
                int reducedDamage = damage * (100 - blockPercentage) / 100;
                warrior.takeDamage(reducedDamage);
                return new int[]{damage, blockPercentage, reducedDamage};
            } else {
                warrior.takeDamage(damage);
                return new int[]{damage, 0, damage};
            }
        }
        return new int[]{0, 0, 0};
    }

    public int executeAction(char actionType) {
        AttackSkill skill = skillManager.getSkill(actionType);
        int actionResult = 0;
        if (skill != null) {
            actionResult = skill.execute(warrior, currentEnemy);
        }
        return actionResult;
    }

    public void increaseTimeSlot() {
        timeSlot++;

        // Update status effects
        currentEnemy.updateStatusEffects();
        warrior.updateStatusEffects();

        // Update cooldowns
        skillManager.updateCooldowns();
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

    public int getRadiationInterval() {
        return radiationInterval;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }
}