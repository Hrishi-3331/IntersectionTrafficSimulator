package Road;

import Animation.Animatable;
import Vehicle.Vehicle;
import java.awt.*;
import java.util.ArrayList;

public abstract class Road implements Animatable {

    public static final int DIRECTION_NORTH = 0;
    public static final int DIRECTION_SOUTH = 1;
    public static final int DIRECTION_EAST = 2;
    public static final int DIRECTION_WEST = 3;
    public static final int INTENSITY_LOW = 0;
    public static final int INTENSITY_MODERATE = 1;
    public static final int INTENSITY_HIGH = 2;

    public static final int ORIENTATION_VERTICAL = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;

    protected String id;
    protected int orientation;
    protected int direction;
    protected int trafficIntensity;

    protected ArrayList<Vehicle> vehicles;

    public Road(String id, int direction) {
        this.direction = direction;
        this.id = id;
        if (direction == Road.DIRECTION_NORTH | direction == Road.DIRECTION_SOUTH) {
            orientation = Road.ORIENTATION_VERTICAL;
        } else if (direction == Road.DIRECTION_EAST | direction == Road.DIRECTION_WEST) {
            orientation = Road.ORIENTATION_HORIZONTAL;
        }
        this.trafficIntensity = Road.INTENSITY_MODERATE;
        vehicles = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getDirection() {
        return direction;
    }

    public void addVehicle(){

    }

    public int getTrafficIntensity() {
        return trafficIntensity;
    }

    public void setTrafficIntensity(int trafficIntensity) {
        this.trafficIntensity = trafficIntensity;
    }

    @Override
    public void draw(Graphics graphics) {

    }

    public int getWaitingCount(){
        int count = 0;
        for (Vehicle vehicle : vehicles){

        }
        return count;
    }
}
