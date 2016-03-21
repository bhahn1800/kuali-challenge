package kuali;

public final class ElevatorSimulator {

    public static void main(String[] args) {
        // validate arguments here

        Integer numberOfElevators = Integer.parseInt(args[0]);
        Integer numberOfFloors = Integer.parseInt(args[1]);

        ElevatorController elevatorController = new ElevatorController(numberOfElevators, numberOfFloors);
        elevatorController.request(1, 5);
    }

}
