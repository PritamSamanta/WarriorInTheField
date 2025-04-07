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

        // Display available attacks
        displayAvailableAttacks(model.getSkillManager().getAvailableSkills());

        // Display enemy frozen status
        if (model.getCurrentEnemy().isFrozen()) {
            System.out.println("Enemy is frozen for " + model.getCurrentEnemy().getFrozenTurns() + " more turn(s)!");
        }

        System.out.println("----------------------------------");
    }

    public void displayAvailableAttacks(List<AttackSkill> availableSkills) {
        System.out.print("Available attacks: ");
        if (availableSkills.isEmpty()) {
            System.out.print("None!");
        } else {
            for (AttackSkill skill : availableSkills) {
                System.out.print(skill.getName() + " (" + skill.getInputKey() + ") ");
            }
        }
        System.out.println();
    }

    public void promptAction() {
        System.out.print("Will you attack (a) or defend (d)? ");
    }

    public void promptAttackType(List<AttackSkill> availableSkills) {
        System.out.print("Choose your attack: ");
        for (AttackSkill skill : availableSkills) {
            System.out.print(skill.getName() + " (" + skill.getInputKey() + ") ");
        }
        System.out.println();
    }

    public void displayInvalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    public void displayInvalidActionInput() {
        System.out.println("Invalid input. Please enter 'a' for attack or 'd' for defend.");
    }

    public void displayInvalidAttackType() {
        System.out.println("Invalid attack type. Please try again.");
    }

    public void displaySkillOnCooldown(String skillName, int cooldown) {
        System.out.println(skillName + " attack is on cooldown for " + cooldown + " more turn(s)!");
    }

    public void displayDefend() {
        System.out.println("You take a defensive stance!");
    }

    public void displayAttackExecution(String skillName, char attackType) {
        switch (attackType) {
            case 'f':
                System.out.println("You cast a freezing spell on the enemy!");
                break;
            case 's':
                System.out.println("You shoot the enemy, dealing 30 damage!");
                break;
            case 'm':
                System.out.println("You cast a magic spell!");
                break;
            default:
                System.out.println("You use " + skillName + "!");
        }
    }

    public void displayMagicDamage(int damage, int remainingHealth) {
        System.out.println("Your magic spell deals " + damage + " damage! Enemy health reduced to " +
                remainingHealth + ".");
    }

    public void displayEnemyFrozen() {
        System.out.println("The enemy is frozen and cannot attack!");
    }

    public void displayBlockedAttack() {
        System.out.println("You successfully block the enemy's attack!");
    }

    public void displayEnemyAttack() {
        System.out.println("The enemy attacks you, dealing 20 damage!");
    }

    public void displayEnemyDefeated(int enemyNumber) {
        System.out.println("You defeated Enemy #" + enemyNumber + "!");
    }

    public void displayHealthBoost(int newHealth) {
        System.out.println("You gained 30 health! Current health: " + newHealth);
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
