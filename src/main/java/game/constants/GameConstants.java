package main.java.game.constants;

public class GameConstants {
    // Player stats
    public static final int INITIAL_WARRIOR_HEALTH = 100;
    public static final int MAX_WARRIOR_HEALTH = 100;

    // Enemy stats
    public static final int MIN_ENEMY_HEALTH = 1;
    public static final int MAX_ENEMY_HEALTH = 99;

    // Damage values
    public static final int ENEMY_ATTACK_DAMAGE = 20;
    public static final int RADIATION_DAMAGE = 10;
    public static final int SHOOT_DAMAGE = 30;
    public static final int HEALTH_BOOST_AFTER_KILL = 30;

    // Time slot constants
    public static final int RADIATION_INTERVAL = 5;

    // Attack cooldowns
    public static final int ATTACK_COOLDOWN_TURNS = 2;

    // Status effect durations
    public static final int FREEZE_DURATION = 2;

    // Input characters
    public static final char ATTACK_INPUT = 'a';
    public static final char DEFEND_INPUT = 'd';
    public static final char FREEZE_INPUT = 'f';
    public static final char SHOOT_INPUT = 's';
    public static final char MAGIC_INPUT = 'm';

    private GameConstants() {
        // Prevent instantiation
    }
}
