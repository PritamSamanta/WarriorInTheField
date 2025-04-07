package main.java.game.model.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillManager {
    private Map<Character, AttackSkill> skills;

    public SkillManager() {
        skills = new HashMap<>();
    }

    public void addSkill(AttackSkill skill) {
        skills.put(skill.getInputKey(), skill);
    }

    public AttackSkill getSkill(char inputKey) {
        return skills.get(inputKey);
    }

    public void updateCooldowns() {
        for (AttackSkill skill : skills.values()) {
            skill.reduceCooldown();
        }
    }

    public List<AttackSkill> getAvailableSkills() {
        List<AttackSkill> availableSkills = new ArrayList<>();
        for (AttackSkill skill : skills.values()) {
            if (skill.isAvailable()) {
                availableSkills.add(skill);
            }
        }
        return availableSkills;
    }

    public List<AttackSkill> getAllSkills() {
        return new ArrayList<>(skills.values());
    }
}
