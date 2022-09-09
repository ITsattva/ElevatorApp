package task.test.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Passenger> people = new ArrayList<>();
    private final int floorNumber;

    public Floor(int startPeopleCount, int floorNumber) {
        this.floorNumber = floorNumber;
        initPeople(startPeopleCount);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Passenger> getPeople() {
        return people;
    }

    public int getPeopleCount() {
        return people.size();
    }

    public void addPassenger(Passenger passenger) {
        people.add(passenger);
    }

    private void initPeople(int startPeopleCount) {
        for (int i = 0; i < startPeopleCount; i++) {
            Passenger passenger = new Passenger(floorNumber);
            people.add(passenger);
        }
    }
}
