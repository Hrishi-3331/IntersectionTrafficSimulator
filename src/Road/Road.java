package Road;

import Animation.Animatable;
import SimulationToolbox.Scenario;
import Vehicle.Vehicle;
import res.GraphicResources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

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
    protected int posX;
    protected int posY;

    protected ArrayList<Vehicle> vehicles;

    public Road(String id, int direction) {
        this.direction = direction;
        this.id = id;
        if (direction == Road.DIRECTION_NORTH | direction == Road.DIRECTION_SOUTH) {
            orientation = Road.ORIENTATION_VERTICAL;
            posY = 0;
            posX = GraphicResources.VERTICAL_ROAD_X_POS;
        } else if (direction == Road.DIRECTION_EAST | direction == Road.DIRECTION_WEST) {
            orientation = Road.ORIENTATION_HORIZONTAL;
            posX = 0;
            posY = GraphicResources.HORIZONTAL_ROAD_Y_POS;
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

    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }

    public int getTrafficIntensity() {
        return trafficIntensity;
    }

    public void setTrafficIntensity(int trafficIntensity) {
        this.trafficIntensity = trafficIntensity;
    }

    public void check(Vehicle vehicle){

    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D canvas = (Graphics2D) graphics;
        Image roadImage = null;
        try{
            switch (this.getOrientation()){
                case Road.ORIENTATION_HORIZONTAL:
                    roadImage = ImageIO.read(getClass().getResourceAsStream(GraphicResources.ROAD_HORIZONTAL));
                    canvas.drawImage(roadImage, posX, posY, GraphicResources.ROAD_HORIZONTAL_LENGTH, GraphicResources.ROAD_WIDTH, null);
                    break;

                case Road.ORIENTATION_VERTICAL:
                    roadImage = ImageIO.read(getClass().getResourceAsStream(GraphicResources.ROAD_VERTICAL));
                    canvas.drawImage(roadImage, posX, posY, GraphicResources.ROAD_WIDTH, GraphicResources.ROAD_VERTICAL_LENGTH, null);
                    break;
            }
        }
        catch (Exception e){
            switch (this.getOrientation()){
                case Road.ORIENTATION_HORIZONTAL:
                    canvas.drawRect(posX, posY, GraphicResources.ROAD_HORIZONTAL_LENGTH, GraphicResources.ROAD_WIDTH);
                    break;

                case Road.ORIENTATION_VERTICAL:
                    canvas.drawRect(posX, posY, GraphicResources.ROAD_WIDTH, GraphicResources.ROAD_VERTICAL_LENGTH);
                    break;
            }
        }
    }

    public int getWaitingCount(){
        int count = 0;
        for (Vehicle vehicle : vehicles){
            if (vehicle.getRunState() == Vehicle.STATE_WAITING) count++;
        }
        return count;
    }

    public boolean newVehicle(int flag, Scenario scenario) {
        if (flag > getTrafficThreshold()){
            boolean add = new Random().nextBoolean();
            if (add){
                Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(), this);
                addVehicle(vehicle);
                switch (vehicle.getFacing()){
                    case Road.DIRECTION_EAST:
                        vehicle.setPosX(GraphicResources.VEHICLE_EAST_POS_X);
                        vehicle.setPosY(GraphicResources.VEHICLE_EAST_POS_Y);
                        break;

                    case Road.DIRECTION_WEST:
                        vehicle.setPosX(GraphicResources.VEHICLE_WEST_POS_X);
                        vehicle.setPosY(GraphicResources.VEHICLE_WEST_POS_Y);
                        break;

                    case Road.DIRECTION_SOUTH:
                        vehicle.setPosX(GraphicResources.VEHICLE_SOUTH_POS_X);
                        vehicle.setPosY(GraphicResources.VEHICLE_SOUTH_POS_Y);
                        break;

                    case Road.DIRECTION_NORTH:
                        vehicle.setPosX(GraphicResources.VEHICLE_NORTH_POS_X);
                        vehicle.setPosY(GraphicResources.VEHICLE_NORTH_POS_Y);
                        break;
                }
                scenario.addComponent(vehicle);
                this.addVehicle(vehicle);
            }
            return add;
        }
        else return false;
    }

    private int getTrafficThreshold(){
        switch (this.getTrafficIntensity()){
            case Road.INTENSITY_LOW:
                return GraphicResources.LOW_TRAFFIC_THRESHOLD_INTERVAL;

            case Road.INTENSITY_MODERATE:
                return GraphicResources.MODERATE_TRAFFIC_THRESHOLD_INTERVAL;

            case Road.INTENSITY_HIGH:
                return GraphicResources.HIGH_TRAFFIC_THRESHOLD_INTERVAL;
        }
        return 3;
    }
}
