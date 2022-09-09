package task.test.models;

import task.test.util.Randomizer;

public class Building {
    public static final int MIN_FLOORS = 5;
    public static final int MAX_FLOORS = 20;
    public static final int FLOOR_START_MAX_CAPACITY = 10;
    private final int SIZE;

    private final Floor[] floors;

    public Building(int floors) {
        SIZE = floors;
        this.floors = new Floor[floors];
        initFloors(floors);
    }

    public int getSize() {
        return SIZE;
    }

    public Floor getFloor(int floor) {
        return floors[floor];
    }

    private void initFloors(int floorsCount) {
        for (int i = 0; i < floorsCount; i++) {
            floors[i] = new Floor(Randomizer.getRandomInt(FLOOR_START_MAX_CAPACITY), i);
        }
    }
}
