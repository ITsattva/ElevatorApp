package task.test;

import task.test.models.Building;
import task.test.models.Elevator;
import task.test.models.Floor;
import task.test.util.Drawer;
import task.test.util.Randomizer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int buildingSize = Randomizer.BUILDING_SIZE;

        Building building = new Building(buildingSize);
        Elevator elevator = new Elevator();
        Drawer drawer = new Drawer();

        int step = 1;

        while (true) {
            System.out.println("Step: #" + step++);

            Floor floor = building.getFloor(elevator.getCurrentFloor());

            elevator.move(floor);
            drawer.draw(building, elevator);

            Thread.sleep(500);
        }
    }
}
