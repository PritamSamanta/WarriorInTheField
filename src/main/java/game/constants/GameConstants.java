package main.java.game.constants;

public class GameConstants {
    // Player stats
    public static final int INITIAL_WARRIOR_HEALTH = 100;
    public static final int MAX_WARRIOR_HEALTH = 100;

    // Enemy stats
    public static final int MIN_ENEMY_HEALTH = 1;
    public static final int MAX_ENEMY_HEALTH = 99;

    // Damage values
    public static final int MIN_ENEMY_ATTACK_DAMAGE = 5;
    public static final int MAX_ENEMY_ATTACK_DAMAGE = 30;
    public static final int MIN_RADIATION_DAMAGE = 2;
    public static final int MAX_RADIATION_DAMAGE = 20;
    public static final int MIN_SHOOT_DAMAGE = 5;
    public static final int MAX_SHOOT_DAMAGE = 45;
    public static final int MIN_HEAL_AMOUNT = 5;
    public static final int MAX_HEAL_AMOUNT = 60;
    public static final int MIN_BLOCK_PERCENTAGE = 5;
    public static final int MAX_BLOCK_PERCENTAGE = 100;

    // Time slot constants
    public static final int MIN_RADIATION_INTERVAL = 3;
    public static final int MAX_RADIATION_INTERVAL = 9;

    // Attack cooldowns
    public static final int ACTION_COOLDOWN_TURNS = 3;

    // Status effect durations
    public static final int FREEZE_DURATION = 2;

    // Input characters
    public static final char FREEZE_INPUT = 'f';
    public static final char SHOOT_INPUT = 's';
    public static final char MAGIC_INPUT = 'm';
    public static final char BLOCK_INPUT = 'b';
    public static final char HEAL_INPUT = 'h';
    public static final char INVISIBLE_INPUT = 'i';

    private GameConstants() {
        // Prevent instantiation
    }
}
