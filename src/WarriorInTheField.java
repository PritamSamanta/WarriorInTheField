import java.util.Scanner;
import java.util.Random;

public class WarriorInTheField {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    // Player stats
    private static int warriorHealth = 100;
    private static int enemiesKilled = 0;
    private static int currentEnemyHealth;
    private static int currentEnemyNumber = 1;

    // Attack cooldowns (0 means available, >0 means turns until available)
    private static int freezeCooldown = 0;
    private static int shootCooldown = 0;
    private static int magicCooldown = 0;

    // Status effects
    private static int enemyFrozenTurns = 0;

    // Time tracking
    private static int timeSlot = 1;

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("Welcome to Warrior in the Field!");
        System.out.println("==================================");
        System.out.println("You are a warrior facing endless enemies.");
        System.out.println("Your goal is to eliminate as many enemies as possible before your health reaches 0.");
        System.out.println("Good luck, warrior!\n");

        // Generate first enemy
        spawnNewEnemy();

        // Main game loop
        while (warriorHealth > 0) {
            displayStatus();
            handleTimeSlot();

            // Check if player died
            if (warriorHealth <= 0) {
                break;
            }

            // Check if enemy died
            if (currentEnemyHealth <= 0) {
                System.out.println("You defeated Enemy #" + currentEnemyNumber + "!");
                enemiesKilled++;

                // Health boost
                warriorHealth += 30;
                if (warriorHealth > 100) warriorHealth = 100;
                System.out.println("You gained 30 health! Current health: " + warriorHealth);

                // Spawn new enemy
                currentEnemyNumber++;
                spawnNewEnemy();
            }

            // Increase time slot counter
            timeSlot++;
        }

        // Game over
        System.out.println("\n==================================");
        System.out.println("Game Over! You have been defeated.");
        System.out.println("Final score: " + enemiesKilled + " enemies defeated");
        System.out.println("==================================");
    }

    private static void spawnNewEnemy() {
        currentEnemyHealth = random.nextInt(99) + 1; // Random health between 1 and 99
        System.out.println("\nEnemy #" + currentEnemyNumber + " appears with " + currentEnemyHealth + " health!");
    }

    private static void displayStatus() {
        System.out.println("\n----------------------------------");
        System.out.println("Time Slot: " + timeSlot);
        System.out.println("Warrior Health: " + warriorHealth);
        System.out.println("Enemy #" + currentEnemyNumber + " Health: " + currentEnemyHealth);

        // Display cooldown status
        System.out.print("Available attacks: ");
        if (freezeCooldown == 0) System.out.print("Freeze (f) ");
        if (shootCooldown == 0) System.out.print("Shoot (s) ");
        if (magicCooldown == 0) System.out.print("Magic (m) ");
        if (freezeCooldown > 0 && shootCooldown > 0 && magicCooldown > 0) System.out.print("None!");
        System.out.println();

        // Display enemy frozen status
        if (enemyFrozenTurns > 0) {
            System.out.println("Enemy is frozen for " + enemyFrozenTurns + " more turn(s)!");
        }

        System.out.println("----------------------------------");
    }

    private static void handleTimeSlot() {
        // Radiation damage check
        if (timeSlot % 5 == 0) {
            warriorHealth -= 10;
            System.out.println("You take 10 damage from radiation exposure!");
        }

        // Get warrior's action
        char action = getWarriorAction();

        // Process action
        boolean warriorDefended = false;

        if (action == 'd') {
            // Defend
            warriorDefended = true;
            System.out.println("You take a defensive stance!");
        } else if (action == 'a') {
            // Attack - choose attack type
            char attackType = getAttackType();

            switch (attackType) {
                case 'f':
                    // Freeze attack
                    System.out.println("You cast a freezing spell on the enemy!");
                    enemyFrozenTurns = 2; // Current turn and next turn
                    freezeCooldown = 3; // Current turn + 2 cooldown turns
                    break;
                case 's':
                    // Shoot attack
                    System.out.println("You shoot the enemy, dealing 30 damage!");
                    currentEnemyHealth -= 30;
                    shootCooldown = 3; // Current turn + 2 cooldown turns
                    break;
                case 'm':
                    // Magic attack
                    int damage = random.nextInt(99) + 1; // Random damage between 1 and 99
                    System.out.println("You cast a magic spell, dealing " + damage + " damage!");
                    currentEnemyHealth -= damage;
                    magicCooldown = 3; // Current turn + 2 cooldown turns
                    break;
                default:
                    System.out.println("Invalid attack! You hesitate and do nothing.");
            }
        }

        // Enemy's turn
        if (enemyFrozenTurns > 0) {
            System.out.println("The enemy is frozen and cannot attack!");
            enemyFrozenTurns--;
        } else if (!warriorDefended) {
            // Enemy attacks if not frozen and warrior didn't defend
            warriorHealth -= 20;
            System.out.println("The enemy attacks you, dealing 20 damage!");
        } else {
            System.out.println("You successfully block the enemy's attack!");
        }

        // Ensure health doesn't go negative
        if (warriorHealth < 0) warriorHealth = 0;
        if (currentEnemyHealth < 0) currentEnemyHealth = 0;

        // Reduce cooldowns
        if (freezeCooldown > 0) freezeCooldown--;
        if (shootCooldown > 0) shootCooldown--;
        if (magicCooldown > 0) magicCooldown--;
    }

    private static char getWarriorAction() {
        char action;
        while (true) {
            System.out.print("Will you attack (a) or defend (d)? ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            action = input.charAt(0);
            if (action == 'a' || action == 'd') {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'a' for attack or 'd' for defend.");
            }
        }
        return action;
    }

    private static char getAttackType() {
        while (true) {
            System.out.print("Choose your attack: ");
            if (freezeCooldown == 0) System.out.print("Freeze (f) ");
            if (shootCooldown == 0) System.out.print("Shoot (s) ");
            if (magicCooldown == 0) System.out.print("Magic (m) ");
            System.out.println();

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            char attackType = input.charAt(0);

            if (attackType == 'f' && freezeCooldown > 0) {
                System.out.println("Freeze attack is on cooldown for " + freezeCooldown + " more turn(s)!");
                continue;
            } else if (attackType == 's' && shootCooldown > 0) {
                System.out.println("Shoot attack is on cooldown for " + shootCooldown + " more turn(s)!");
                continue;
            } else if (attackType == 'm' && magicCooldown > 0) {
                System.out.println("Magic attack is on cooldown for " + magicCooldown + " more turn(s)!");
                continue;
            }

            if (attackType == 'f' || attackType == 's' || attackType == 'm') {
                return attackType;
            } else {
                System.out.println("Invalid attack type. Please try again.");
            }
        }
    }
}