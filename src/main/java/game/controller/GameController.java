package main.java.game.controller;

import main.java.game.model.*;
import main.java.game.model.skill.*;
import main.java.game.view.GameView;
import main.java.game.constants.GameConstants;

public class GameController {
    private GameModel model;
    private GameView view;
    private InputHandler inputHandler;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.inputHandler = new InputHandler();
    }

    public void startGame() {
        view.displayWelcomeMessage();

        // Main game loop
        while (!model.isGameOver()) {
            // Display current status
            view.displayStatus(model);

            // Process time slot
            processTimeSlot();

            // Check if game is over
            if (model.isGameOver()) {
                break;
            }

            // Check if enemy is defeated
            if (model.isEnemyDefeated()) {
                view.displayEnemyDefeated(model.getCurrentEnemy().getEnemyNumber());
                model.handleEnemyDefeat();
                view.displayHealthBoost(model.getWarrior().getHealth());
                view.displayNewEnemy(model.getCurrentEnemy());
            }

            // Increase time slot
            model.increaseTimeSlot();
        }

        // Display game over
        view.displayGameOver(model.getEnemiesKilled());
    }

    private void processTimeSlot() {
        // Check for radiation damage
        model.processRadiationDamage();

        // Get warrior's action
        char action = inputHandler.getWarriorAction(view);

        // Process action
        if (action == GameConstants.DEFEND_INPUT) {
            // Defend
            model.getWarrior().setDefending(true);
            view.displayDefend();
        } else if (action == GameConstants.ATTACK_INPUT) {
            // Attack - choose attack type
            char attackType = inputHandler.getAttackType(view, model.getSkillManager());

            // Execute attack
            AttackSkill skill = model.getSkillManager().getSkill(attackType);
            if (skill != null && skill.isAvailable()) {
                view.displayAttackExecution(skill.getName(), attackType);

                int enemyHealthBeforeAttack = getCurrentEnemyHealth();
                model.executeAttack(attackType);

                int enemyHealthAfterAttack = getCurrentEnemyHealth();
                int damageDone = enemyHealthBeforeAttack - enemyHealthAfterAttack;
                // Display additional information for magic attack
                if (attackType == GameConstants.MAGIC_INPUT) {
                    view.displayMagicDamage(damageDone,enemyHealthAfterAttack);
                }
            }
        }

        // Enemy's turn
        if (model.getCurrentEnemy().isFrozen()) {
            view.displayEnemyFrozen();
        } else if (model.getWarrior().isDefending()) {
            view.displayBlockedAttack();
        } else {
            model.processEnemyAttack();
            view.displayEnemyAttack();
        }
    }

    private int getCurrentEnemyHealth() {
        return model.getCurrentEnemy().getHealth();
    }
}
