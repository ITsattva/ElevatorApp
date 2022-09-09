package task.test.models;

import task.test.util.Randomizer;

public class Passenger {
    private int neededFloor;
    private int currentFloor;

    public Passenger(int currentFloor) {
        this.currentFloor = currentFloor;
        this.neededFloor = generateNeededFloor();
    }

    private int generateNeededFloor() {
        int randomFloor;
        do {
            randomFloor = Randomizer.getRandomInt(Randomizer.BUILDING_SIZE);
        } while (randomFloor == currentFloor);

        return randomFloor;
    }

    public void exitFromTheElevator(int currentFloor) {
        this.currentFloor = currentFloor;
        neededFloor = generateNeededFloor();
    }

    public int getNeededFloor() {
        return neededFloor;
    }


}
