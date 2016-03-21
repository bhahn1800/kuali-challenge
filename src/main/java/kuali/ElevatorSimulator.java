package kuali;

import java.util.Random;

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
public final class ElevatorSimulator {

    public static void main(String[] args) {
//        runLightLoad();
//        runMediumLoad();
        runHeavyLoad();
    }

    private static void runLightLoad() {
        Integer numberOfElevators = 2;
        Integer numberOfFloors = 5;

        ElevatorController elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);
        elevatorController.request(1, 5);
        elevatorController.moveElevators(2);
        elevatorController.request(1, 4);
        elevatorController.moveElevators(1);
        elevatorController.request(3, 2);
        elevatorController.moveElevators(1);
        elevatorController.request(1, 3);
        elevatorController.moveElevators(10);
    }

    private static void runMediumLoad() {
        Integer numberOfElevators = 5;
        Integer numberOfFloors = 15;

        ElevatorController elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);
        elevatorController.request(1, 5);
        elevatorController.moveElevators(2);
        elevatorController.request(1, 7);
        elevatorController.moveElevators(1);
        elevatorController.request(6, 2);
        elevatorController.moveElevators(1);
        elevatorController.request(1, 3);
        elevatorController.moveElevators(3);
        elevatorController.request(1, 15);
        elevatorController.moveElevators(2);
        elevatorController.request(2, 8);
        elevatorController.moveElevators(2);
        elevatorController.request(15, 1);
        elevatorController.moveElevators(5);
        elevatorController.request(14, 10);
        elevatorController.moveElevators(20);
    }

    private static void runHeavyLoad() {
        Integer numberOfElevators = 10;
        Integer numberOfFloors = 200;

        ElevatorController elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);
        Random r = new Random();

        for (int i = 0; i < 10000; i++) {
            elevatorController.request(1, r.nextInt(200) + 2);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(1, r.nextInt(200) + 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(1, r.nextInt(200) + 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(1, r.nextInt(200) + 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(1, r.nextInt(200) + 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(r.nextInt(200) + 1, 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(r.nextInt(200) + 1, 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(r.nextInt(200) + 1, 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(r.nextInt(200) + 1, 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
            elevatorController.request(r.nextInt(200) + 1, 1);
            elevatorController.moveElevators(r.nextInt(20) + 1);
        }
    }

}
