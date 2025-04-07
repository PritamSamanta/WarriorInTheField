package main.java.game.view;

import main.java.game.model.GameModel;
import main.java.game.model.entity.Enemy;
import main.java.game.model.skill.AttackSkill;

import java.util.List;

public class GameView {
    public void displayWelcomeMessage() {
        System.out.println("==================================");
        System.out.println("Welcome to Warrior in the Field!");
        System.out.println("==================================");
        System.out.println("You are a warrior facing endless enemies.");
        System.out.println("Your goal is to eliminate as many enemies as possible before your health reaches 0.");
        System.out.println("Good luck, warrior!\n");
    }

    public void displayStatus(GameModel model) {
        System.out.println("\n----------------------------------");
        System.out.println("Time Slot: " + model.getTimeSlot());
        System.out.println("Warrior Health: " + model.getWarrior().getHealth());
        System.out.println("Enemy #" + model.getCurrentEnemyNumber() + " Health: " + model.getCurrentEnemy().getHealth());
        System.out.println("Next radiation damage in: " +
                ((model.getRadiationInterval() - (model.getTimeSlot() % model.getRadiationInterval())) %
                        model.getRadiationInterval() == 0 ? model.getRadiationInterval() :
                        (model.getRadiationInterval() - (model.getTimeSlot() % model.getRadiationInterval()))) +
                " turns");

        // Display available actions
        displayAvailableActions(model.getSkillManager().getAvailableSkills());

        // Display enemy frozen status
        if (model.getCurrentEnemy().isFrozen()) {
            System.out.println("Enemy is frozen for " + model.getCurrentEnemy().getFrozenTurns() + " more turn(s)!");
        }

        System.out.println("----------------------------------");
    }

    public void displayAvailableActions(List<AttackSkill> availableSkills) {
        System.out.print("Available actions: ");
        if (availableSkills.isEmpty()) {
            System.out.print("None!");
        } else {
            for (AttackSkill skill : availableSkills) {
                System.out.print(skill.getName() + " (" + skill.getInputKey() + ") ");
            }
        }
        System.out.println();
    }

    public void promptActionType(List<AttackSkill> availableSkills) {
        System.out.print("Choose your action: ");
        for (AttackSkill skill : availableSkills) {
            System.out.print(skill.getName() + " (" + skill.getInputKey() + ") ");
        }
        System.out.println();
    }

    public void displayInvalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    public void displayInvalidActionType() {
        System.out.println("Invalid action type. Please try again.");
    }

    public void displaySkillOnCooldown(String skillName, int cooldown) {
        System.out.println(skillName + " is on cooldown for " + cooldown + " more turn(s)!");
    }

    public void displayBlockAction(int blockPercentage) {
        System.out.println("You take a defensive stance, ready to block " + blockPercentage + "% of incoming damage!");
    }

    public void displayHealAction(int healAmount) {
        System.out.println("You use a healing potion, restoring " + healAmount + " health!");
    }

    public void displayShootAction(int damage) {
        System.out.println("You shoot the enemy, dealing " + damage + " damage!");
    }

    public void displayMagicAction(int damage) {
        System.out.println("Your magic spell deals " + damage + " damage!");
    }

    public void displayFreezeAction() {
        System.out.println("You cast a freezing spell on the enemy!");
    }

    public void displayInvisibleAction() {
        System.out.println("You activate your invisibility cloak and disappear from sight!");
    }

    public void displayRadiationDamage(int damage) {
        System.out.println("You take " + damage + " radiation damage!");
    }

    public void displayEnemyFrozen() {
        System.out.println("The enemy is frozen and cannot attack!");
    }

    public void displayInvisibleFromEnemy() {
        System.out.println("The enemy cannot see you and misses their attack!");
    }

    public void displayPartialBlockedAttack(int originalDamage, int blockPercentage, int reducedDamage) {
        System.out.println("The enemy attacks for " + originalDamage + " damage, but you block " +
                blockPercentage + "% and only take " + reducedDamage + " damage!");
    }

    public void displayEnemyAttack(int damage) {
        System.out.println("The enemy attacks you, dealing " + damage + " damage!");
    }

    public void displayEnemyDefeated(int enemyNumber) {
        System.out.println("You defeated Enemy #" + enemyNumber + "!");
    }

    public void displayNewEnemy(Enemy enemy) {
        System.out.println("\nEnemy #" + enemy.getEnemyNumber() + " appears with " + enemy.getHealth() + " health!");
    }

    public void displayGameOver(int enemiesKilled) {
        System.out.println("\n==================================");
        System.out.println("Game Over! You have been defeated.");
        System.out.println("Final score: " + enemiesKilled + " enemies defeated");
        System.out.println("==================================");
    }
}
