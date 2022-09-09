package task.test.models;

import task.test.util.Randomizer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Elevator {
    public enum Direction {UP, DOWN}

    private static final int CAPACITY = 5;

    private Direction direction;
    private Direction previousDirection;
    private final ArrayList<Passenger> passengers = new ArrayList<>();

    private int currentFloor;
    private int maxFloorForCurrentIteration;
    private int minFloorForCurrentIteration;

    public Elevator() {
        currentFloor = 0;
        direction = Direction.UP;
    }

    public void move(Floor floor) {
        if (isEmpty())
            setDirectionToMajority(floor);

        letIn(floor);

        switch (direction) {
            case UP -> {
                if (currentFloor < maxFloorForCurrentIteration) {
                    currentFloor++;
                } else {
                    changeDirection();
                }
            }
            case DOWN -> {
                if (currentFloor > minFloorForCurrentIteration) {
                    currentFloor--;
                } else {
                    changeDirection();
                }
            }
        }

        letOut(floor);

    }

    public int getPassengersCount() {
        return passengers.size();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    private void changeDirection() {
        direction = direction == Direction.UP ? Direction.DOWN : Direction.UP;
    }

    public void letIn(Floor floor) {
        Iterator<Passenger> iterator = floor.getPeople().listIterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (isOnTheRightDirection(passenger) && (passengers.size() < CAPACITY)) {
                passengers.add(passenger);
                iterator.remove();
            }
        }

        resetFloors();
    }

    public void letOut(Floor floor) {
        Iterator<Passenger> iterator = passengers.listIterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getNeededFloor() == floor.getFloorNumber()) {
                passenger.exitFromTheElevator(floor.getFloorNumber());
                floor.addPassenger(passenger);
                iterator.remove();
            }
        }

        resetFloors();
    }

    private boolean isOnTheRightDirection(Passenger passenger) {
        switch (direction) {
            case UP:
                if (passenger.getNeededFloor() > currentFloor) {
                    return true;
                }
            case DOWN:
                if (passenger.getNeededFloor() < currentFloor) {
                    return true;
                }
        }
        return false;
    }

    private void setDirectionToMajority(Floor floor) {
        if (floor.getPeopleCount() == 0)
            return;

        direction = getDirectionOfMajority(floor);
    }

    private Direction getDirectionOfMajority(Floor floor) {
        int moveUp = 0;
        int moveDown = 0;

        for (Passenger passenger : floor.getPeople()) {
            if (getPassengerDirection(passenger) == Direction.UP) {
                moveUp++;
            } else {
                moveDown++;
            }
        }

        return moveUp >= moveDown ? Direction.UP : Direction.DOWN;
    }

    private Direction getPassengerDirection(Passenger passenger) {
        if (passenger.getNeededFloor() > currentFloor) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }

    public int getMaxFloorForCurrentIteration() {
        return maxFloorForCurrentIteration;
    }

    public int getMinFloorForCurrentIteration() {
        return minFloorForCurrentIteration;
    }

    private void resetMaxNeededFloor() {
        if (passengers.size() != 0) {
            maxFloorForCurrentIteration = passengers.stream().max(Comparator.comparingInt(Passenger::getNeededFloor)).get().getNeededFloor();
        } else {
            maxFloorForCurrentIteration = Randomizer.BUILDING_SIZE - 1;
        }
    }

    private void resetMinNeededFloor() {
        if (passengers.size() != 0) {
            minFloorForCurrentIteration = passengers.stream().min(Comparator.comparingInt(Passenger::getNeededFloor)).get().getNeededFloor();
        } else {
            minFloorForCurrentIteration = 0;
        }
    }

    private void resetFloors() {
        resetMaxNeededFloor();
        resetMinNeededFloor();
    }

    private boolean isEmpty() {
        return passengers.isEmpty();
    }
}
