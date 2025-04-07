package main.java.game.controller;

import main.java.game.model.skill.AttackSkill;
import main.java.game.model.skill.SkillManager;
import main.java.game.view.GameView;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public char getActionType(GameView view, SkillManager skillManager) {
        while (true) {
            view.promptActionType(skillManager.getAvailableSkills());

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                view.displayInvalidInput();
                continue;
            }

            char actionType = input.charAt(0);
            AttackSkill skill = skillManager.getSkill(actionType);

            if (skill == null) {
                view.displayInvalidActionType();
                continue;
            }

            if (!skill.isAvailable()) {
                view.displaySkillOnCooldown(skill.getName(), skill.getCooldown());
                continue;
            }

            return actionType;
        }
    }
}
