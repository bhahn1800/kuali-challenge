package kuali;

import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Elevator> activeElevators;

    public ElevatorController(int numElevators, int numFloors) {
        this.numElevators = numElevators;
        this.numFloors = numFloors;
        start();
    }

    private void start() {
        this.elevatorList = new ArrayList<>();
        this.activeElevators = new ArrayList<>();

        for (int i = 0; i < numElevators; i++) {
            this.elevatorList.add(new Elevator(i + 1));
        }
    }

    public void moveElevators(int numMoves) {
        for (int i = 0; i < numMoves; i++) {
            Iterator<Elevator> elevatorIterator = activeElevators.iterator();

            while (elevatorIterator.hasNext()) {
                Elevator active = elevatorIterator.next();
                move(active);

                if (active.getDestinationFloor() == null) {
                    elevatorIterator.remove();
                }
            }
        }
    }

    private void move(Elevator elevator) {
        if (elevator.isGoingUp()) {
            if (elevator.getCurrentFloor() < numFloors) {
                elevator.incFloor();
            }
        } else {
            if (elevator.getCurrentFloor() > 1) {
                elevator.decFloor();
            }
        }
    }

    public void request(int fromFloor, int toFloor) {
        if (toFloor > numFloors) throw new IllegalArgumentException(toFloor + " exceeds the max floor count of " + numFloors);
        if (fromFloor > numFloors) throw new IllegalArgumentException(fromFloor + " exceeds the max floor count of " + numFloors);
        if (fromFloor == toFloor) throw new IllegalArgumentException("cannot request the floor that you are currently on");

        Elevator elevator = getElevator(fromFloor, toFloor);
        if (elevator == null) {
            System.err.println("no available elevators - please call the service technician");
            return;
        }

        elevator.setDestinationFloor(toFloor);

        if (!activeElevators.contains(elevator)) {
            activeElevators.add(elevator);
        }
    }

    private Elevator getElevator(int fromFloor, int toFloor) {
        Elevator unoccupiedOnCurrentFloor = getUnoccupiedOnCurrentFloor(fromFloor);
        if (unoccupiedOnCurrentFloor != null) {
            if (!unoccupiedOnCurrentFloor.isInMaintenanceMode()) {
                return unoccupiedOnCurrentFloor;
            }
        }

        Elevator occupiedApproaching = getOccupiedApproaching(fromFloor, toFloor);
        if (occupiedApproaching != null) {
            if (!occupiedApproaching.isInMaintenanceMode()) {
                return occupiedApproaching;
            }
        }

        Elevator closestUnoccupied = getClosestUnoccupied(fromFloor);
        if (closestUnoccupied != null) {
            if (!closestUnoccupied.isInMaintenanceMode()) {
                return closestUnoccupied;
            }
        }

        return null;
    }

    private Elevator getUnoccupiedOnCurrentFloor(int fromFloor) {
        for (Elevator elevator : elevatorList) {
            if (!elevator.isOccupied()) {
                // if already on current floor, then highest priority
                if (elevator.getCurrentFloor() == fromFloor) {
                    return elevator;
                }
            }
        }

        return null;
    }

    private Elevator getClosestUnoccupied(int fromFloor) {
        Elevator closest = null;

        for (Elevator elevator : elevatorList) {
            if (!elevator.isOccupied()) {
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

    private Elevator getOccupiedApproaching(int fromFloor, int toFloor) {
        boolean goingUp = (toFloor > fromFloor);
        Elevator occupiedApproaching = null;

        for (Elevator elevator : elevatorList) {
            if (elevator.isOccupied()) {
                if (goingUp) {
                    if (elevator.isGoingUp()) {
                        if (elevator.getCurrentFloor() <= fromFloor) {
                            if (elevator.getDestinationFloor() >= toFloor) {
                                occupiedApproaching = elevator;
                                occupiedApproaching.addStop(toFloor);
                                break;
                            }
                        }
                    }
                } else {
                    if (!elevator.isGoingUp()) {
                        if (elevator.getCurrentFloor() >= fromFloor) {
                            if (elevator.getDestinationFloor() <= toFloor) {
                                occupiedApproaching = elevator;
                                occupiedApproaching.addStop(toFloor);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return occupiedApproaching;
    }

}
