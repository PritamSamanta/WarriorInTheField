package main.java.game.controller;

import main.java.game.model.*;
import main.java.game.model.skill.*;
import main.java.game.view.GameView;

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
        int radiationDamage = model.processRadiationDamage();
        if (radiationDamage > 0) {
            view.displayRadiationDamage(radiationDamage);
        }

        // Get warrior's action
        char action = inputHandler.getActionType(view, model.getSkillManager());

        // Execute action
        AttackSkill skill = model.getSkillManager().getSkill(action);
        if (skill != null && skill.isAvailable()) {
            int actionResult = -1;

            // Execute the action and store the result
            switch (action) {
                case 'b': // Block
                    actionResult = model.executeAction(action);
                    view.displayBlockAction(actionResult);
                    break;
                case 'h': // Heal
                    actionResult = model.executeAction(action);
                    view.displayHealAction(actionResult);
                    break;
                case 's': // Shoot
                    actionResult = model.executeAction(action);
                    view.displayShootAction(actionResult);
                    break;
                case 'm': // Magic
                    actionResult = model.executeAction(action);
                    view.displayMagicAction(actionResult);
                    break;
                case 'f': // Freeze
                    model.executeAction(action);
                    view.displayFreezeAction();
                    break;
                case 'i': // Invisible
                    model.executeAction(action);
                    view.displayInvisibleAction();
                    break;
            }
        }

        // Enemy's turn
        if (model.getCurrentEnemy().isFrozen()) {
            view.displayEnemyFrozen();
        } else if (model.getWarrior().isInvisible()) {
            view.displayInvisibleFromEnemy();
        } else {
            int[] attackResult = model.processEnemyAttack();
            int originalDamage = attackResult[0];
            int blockPercentage = attackResult[1];
            int reducedDamage = attackResult[2];

            if (model.getWarrior().isBlocking()) {
                view.displayPartialBlockedAttack(originalDamage, blockPercentage, reducedDamage);
            } else {
                view.displayEnemyAttack(originalDamage);
            }
        }
    }
}
