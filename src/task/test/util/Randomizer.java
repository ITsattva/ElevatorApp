package task.test.util;

import task.test.models.Building;

import java.util.Random;

public class Randomizer {
    public static final int BUILDING_SIZE = getRandomFloorsCount();

    public static int getRandomInt(int max) {
        Random random = new Random();

        return random.nextInt(max);
    }

    public static int getRandomFloorsCount() {
        Random random = new Random();

        return Building.MIN_FLOORS + random.nextInt(Building.MAX_FLOORS - Building.MIN_FLOORS + 1);
    }
}
