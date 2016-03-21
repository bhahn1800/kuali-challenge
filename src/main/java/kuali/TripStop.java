package kuali;

public class TripStop {

    private int floorStart;
    private int floorStop;

    public TripStop(int floorStart, int floorStop) {
        this.floorStart = floorStart;
        this.floorStop = floorStop;
    }

    public int getFloorStart() {
        return floorStart;
    }

    public int getFloorStop() {
        return floorStop;
    }
}
