package main.java.game.util;

import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();

    private RandomGenerator() {
        // Prevent instantiation
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
