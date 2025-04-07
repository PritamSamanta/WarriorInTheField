# Warrior in the Field

A text-based command-line RPG battle game written in Java where you play as a warrior fighting against an endless stream of enemies.

## Game Description

In "Warrior in the Field," you are a lone warrior who must face waves of enemies in a hostile environment. Your goal is to defeat as many enemies as possible before your health reaches zero. The game tracks your score based on how many enemies you've eliminated.

## Game Mechanics

### Basic Stats
- **Warrior Health**: Starts at 100
- **Enemy Health**: Random value between 1 and 99 for each enemy

### Combat System
Each round of combat occurs in a "Time Slot," where you can choose to either attack or defend:

#### Defense
- When defending, you block all damage from the enemy for that time slot
- Input: Press 'd' to defend

#### Attacks
When choosing to attack (press 'a'), you have three special abilities:

1. **Freeze** (f): Freezes the enemy, preventing them from attacking for the current and next time slot
2. **Shoot** (s): Deals 30 damage to the enemy
3. **Magic** (m): Deals random damage between 1 and 99 to the enemy

#### Attack Cooldowns
- Each attack ability has a 2-turn cooldown after use
- You cannot use an attack again until its cooldown expires

### Environmental Hazards
- Every 5 time slots, you take 10 damage from radiation exposure in the field

### Rewards
- After defeating an enemy, you gain +30 health (maximum health is 100)
- A new enemy will appear after each victory

### Game Over
- The game ends when your health reaches 0
- Your final score is the number of enemies you defeated

## How to Play

### Installation
1. Make sure you have Java installed on your system
2. Compile the code:
   ```
   javac -d out src/main/java/game/*.java src/main/java/game/*/*.java
   ```
3. Run the game:
   ```
   java -cp out game.WarriorInTheFieldGame
   ```

### Controls
- At each time slot, you'll be prompted to choose your action
- Press 'a' to attack or 'd' to defend
- If attacking, you'll be prompted to choose one of your available attacks:
    - 'f' for Freeze
    - 's' for Shoot
    - 'm' for Magic

### Tips
- Balance between attacking and defending
- Use Freeze strategically to avoid damage for two consecutive turns
- Consider your ability cooldowns when planning your strategy
- Remember that radiation damage occurs every 5 turns
- Try to defeat enemies quickly to replenish your health

## Project Structure

The project is organized using a modular architecture following MVC pattern and SOLID principles:

### Packages
- **game** - Main package
    - **constants** - Game constants and configuration
    - **controller** - Input handling and game flow control
    - **model** - Game state and business logic
        - **entity** - Game characters (Warrior, Enemy)
        - **skill** - Attack abilities implementation
    - **util** - Utility classes
    - **view** - User interface and display

### Design Patterns
The code implements several design patterns to ensure maintainability and extensibility:

1. **MVC (Model-View-Controller)**
    - **Model**: Manages game state and logic
    - **View**: Handles display and user interface
    - **Controller**: Controls game flow and processes input

2. **Strategy Pattern**
    - Different attack skills implement a common interface
    - Easy to add new skills without modifying existing code

3. **Factory Pattern**
    - Used for skill creation and management

4. **Singleton Pattern**
    - For utility classes like RandomGenerator

## Extending the Game

The refactored architecture makes it easy to extend the game with new features:

### Adding New Skills
1. Create a new class that extends `AttackSkill`
2. Implement the `execute` method
3. Add the skill to the `SkillManager` in the `GameModel` constructor

### Adding New Enemy Types
1. Create a subclass of `Enemy`
2. Override methods to implement special behavior
3. Modify the `spawnNewEnemy` method in `GameModel` to create different enemy types

### Adding New Game Mechanics
The modular design allows for easy integration of new mechanics such as:
- Different environments with unique effects
- Items and power-ups
- Special events during gameplay
- Multiple levels with increasing difficulty

## License
This game is provided as open-source software and may be modified and distributed freely.