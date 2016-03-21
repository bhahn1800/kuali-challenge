package kuali;

import java.util.Random;

public final class ElevatorSimulator {

    public static void main(String[] args) {
//        runLightLoad();
//        runMediumLoad();
//        runHeavyLoad();
        runSingleElevator();
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
        int maxMoves = 20;

        for (int i = 0; i < 500; i++) {
            elevatorController.request(1, r.nextInt(198) + 2);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request(1, r.nextInt(198) + 2);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request(1, r.nextInt(198) + 2);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request(1, r.nextInt(198) + 2);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request(1, r.nextInt(198) + 2);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request( r.nextInt(198) + 2, 1);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request( r.nextInt(198) + 2, 1);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request( r.nextInt(198) + 2, 1);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request( r.nextInt(198) + 2, 1);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);

            elevatorController.request( r.nextInt(198) + 2, 1);
            elevatorController.moveElevators(r.nextInt(maxMoves) + 1);
        }
    }

    private static void runSingleElevator() {
        Integer numberOfElevators = 1;
        Integer numberOfFloors = 10;

        ElevatorController elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);

        System.out.println("1");
        elevatorController.request(1, 10);
        System.out.println("2");
        elevatorController.moveElevators(1);
        System.out.println("3");
        elevatorController.request(3, 8);
        System.out.println("4");
        elevatorController.moveElevators(10);
    }

}
