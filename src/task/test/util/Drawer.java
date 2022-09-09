package task.test.util;

import task.test.models.Building;
import task.test.models.Elevator;
import task.test.models.Floor;

public class Drawer {

    public void draw(Building building, Elevator elevator) {
        int buildingSize = building.getSize();
        int currentFloor = elevator.getCurrentFloor();

        for (int i = buildingSize - 1; i >= 0; i--) {
            boolean isCurrentFloor = currentFloor == i;
            drawStage(building.getFloor(i), elevator, isCurrentFloor);
        }

        System.out.println();
    }

    private String drawFloor(Floor floor) {
        StringBuilder floorSchema = new StringBuilder();

        int passengersCount = floor.getPeopleCount();
        String passengers = "*".repeat(passengersCount);
        floorSchema.append(passengers);

        while (floorSchema.length() <= 50)
            floorSchema.insert(0, " ");

        floorSchema.insert(0, "|");

        return floorSchema.toString();
    }

    private String drawElevator(Elevator elevator) {
        StringBuilder elevatorSchema = new StringBuilder("||");

        int passengersCount = elevator.getPassengersCount();
        elevatorSchema.append("*".repeat(passengersCount));

        while (elevatorSchema.length() <= 6)
            elevatorSchema.append(" ");

        elevatorSchema.append("||");

        return elevatorSchema.toString();
    }

    private void drawStage(Floor floor, Elevator elevator, boolean isCurrentFloor) {

        if (isCurrentFloor) {
            System.out.println(drawFloor(floor) + drawElevator(elevator) + floor.getFloorNumber() + additionalInfoAboutElevator(elevator));
        } else {
            System.out.println(drawFloor(floor) + drawWell() + (floor.getFloorNumber()));
        }
    }

    private String additionalInfoAboutElevator(Elevator elevator) {
        Elevator.Direction currentDirection = elevator.getDirection();
        String additionalInfo = " <--- Lift moves " + currentDirection.toString().toLowerCase() + " to " + (currentDirection == Elevator.Direction.UP ? elevator.getMaxFloorForCurrentIteration() : elevator.getMinFloorForCurrentIteration()) + " floor";

        return additionalInfo;
    }

    private String drawWell() {
        return "|       |";
    }

}
