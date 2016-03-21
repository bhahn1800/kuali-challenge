package kuali;

import java.util.ArrayList;
import java.util.List;

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
public class ElevatorController {

    private int numElevators;
    private int numFloors;

    private List<Elevator> elevatorList;

    public ElevatorController(int numElevators, int numFloors) {
        this.numElevators = numElevators;
        this.numFloors = numFloors;
        start();
    }

    private void start() {
        this.elevatorList = new ArrayList<>();

        for (int i = 0; i < numElevators; i++) {
            this.elevatorList.add(new Elevator());
        }
    }

    public void request(int fromFloor, int toFloor) {
        if (toFloor > numFloors) throw new IllegalArgumentException(toFloor + " exceeds the max floor count of " + numFloors);
        if (fromFloor == toFloor) throw new IllegalArgumentException("cannot request the floor that you are currently on");

        Elevator closestUnoccupied = getClosestUnoccupied(fromFloor);
        Elevator occupiedApproaching = getOccupiedApproaching(fromFloor, toFloor);
    }

    private Elevator getOccupiedApproaching(int fromFloor, int toFloor) {
        boolean goingUp = (toFloor > fromFloor);
        Elevator occupiedApproaching = null;

        for (Elevator elevator : elevatorList) {
            if (elevator.isOccupied()) {
                if (goingUp) {
                    if (elevator.isGoingUp()) {

                    }
                } else {
                    if (!elevator.isGoingUp()) {

                    }
                }
            }
        }

        return occupiedApproaching;
    }

    private Elevator getClosestUnoccupied(int fromFloor) {
        Elevator closest = null;

        for (Elevator elevator : elevatorList) {
            if (!elevator.isOccupied()) {
                // if already on current floor, then highest priority
                if (elevator.getCurrentFloor() == fromFloor) {
                    closest = elevator;
                    break;
                }

                if (closest == null) {
                    closest = elevator;
                } else {
                    int currentFloor = elevator.getCurrentFloor();
                    int currDistanceInFloors = Math.abs(currentFloor - fromFloor);
                    int closestDistanceInFloors = Math.abs(closest.getCurrentFloor() - fromFloor);

                    if (currDistanceInFloors < closestDistanceInFloors) {
                        closest = elevator;
                    }
                }
            }
        }

        return closest;
    }

}
