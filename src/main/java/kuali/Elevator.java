package kuali;

import java.util.HashSet;
import java.util.Set;

/**
 * Initialize the elevator simulation with the desired number of elevators, and the desired
 number of floors. Assume ground/min of 1.
 2. Each elevator will report as is moves from floor to floor.
 3. Each elevator will report when it opens or closes its doors.
 4. An elevator cannot proceed above the top floor.

 5. An elevator cannot proceed below the ground floor (assume 1 as the min).

 6. An elevator request can be made at any floor, to go to any other floor.

 7. When an elevator request is made, the unoccupied elevator closest to it will answer
 the call, unless an occupied elevator is moving and will pass that floor on its way. The
 exception is that if an unoccupied elevator is already stopped at that floor, then it will
 always have the highest priority answering that call.

 8. The elevator should keep track of how many trips it has made, and how many floors it
 has passed. The elevator should go into maintenance mode after 100 trips, and stop
 functioning until serviced, therefore not be available for elevator calls.
 */
public class Elevator {

    public static enum DoorStatus {
        Open, Close
    }

    private int elevatorId;
    private DoorStatus doorStatus = DoorStatus.Close;
    private int currentFloor = 1;

    private Integer destinationFloor;
    private Set<Integer> stops = new HashSet<>();

    private int tripCount;
    private int floorCount;

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public boolean isOccupied() {
        return destinationFloor != null && DoorStatus.Close.equals(doorStatus);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int incFloor() {
        this.currentFloor++;
        this.floorCount++;

        if (DoorStatus.Open.equals(doorStatus)) {
            System.out.println("Elevator " + elevatorId + " :: shutting doors :: (total floor count = " + floorCount + ", trip count = " + tripCount + ")");
            doorStatus = DoorStatus.Close;
        }

        System.out.println("Elevator " + elevatorId + " :: moving up to floor " + currentFloor + " :: (total floor count = " + floorCount + ", trip count = " + tripCount + ")");
                validateTrip();

        return currentFloor;
    }

    public int decFloor() {
        this.currentFloor--;
        this.floorCount++;

        if (DoorStatus.Open.equals(doorStatus)) {
            System.out.println("Elevator " + elevatorId + " :: shutting doors (total floor count = " + floorCount + ", trip count = " + tripCount + ")");
            doorStatus = DoorStatus.Close;
        }

        System.out.println("Elevator " + elevatorId + " :: moving down to floor " + currentFloor + " :: (total floor count = " + floorCount + ", trip count = " + tripCount + ")");

        validateTrip();

        return currentFloor;
    }

    private void validateTrip() {
        if (currentFloor == destinationFloor) {
            tripCount++;

            System.out.println("Elevator " + elevatorId + " :: opening doors (total floor count = " + floorCount + ", trip count = " + tripCount + ")");

            doorStatus = DoorStatus.Open;
            destinationFloor = null;
        }
    }

    public boolean isGoingUp() {
        return currentFloor < destinationFloor;
    }

    public Integer getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void addStop(int stopFloor) {
        this.stops.add(stopFloor);
    }

    public boolean isInMaintenanceMode() {
        return (tripCount >= 100);
    }

}
