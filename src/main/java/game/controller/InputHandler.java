package main.java.game.controller;

import main.java.game.constants.GameConstants;
import main.java.game.model.skill.AttackSkill;
import main.java.game.model.skill.SkillManager;
import main.java.game.view.GameView;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public char getWarriorAction(GameView view) {
        char action;
        while (true) {
            view.promptAction();
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                view.displayInvalidInput();
                continue;
            }

            action = input.charAt(0);
            if (action == GameConstants.ATTACK_INPUT || action == GameConstants.DEFEND_INPUT) {
                break;
            } else {
                view.displayInvalidActionInput();
            }
        }
        return action;
    }

    public char getAttackType(GameView view, SkillManager skillManager) {
        while (true) {
            view.promptAttackType(skillManager.getAvailableSkills());

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                view.displayInvalidInput();
                continue;
            }

            char attackType = input.charAt(0);
            AttackSkill skill = skillManager.getSkill(attackType);

            if (skill == null) {
                view.displayInvalidAttackType();
                continue;
            }

            if (!skill.isAvailable()) {
                view.displaySkillOnCooldown(skill.getName(), skill.getCooldown());
                continue;
            }

            return attackType;
        }
    }
}
