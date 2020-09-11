package Vehicle;

import Animation.Animatable;
import Road.Road;
import SimulationToolbox.Simulatable;
import res.GraphicResources;
import javax.imageio.ImageIO;
import java.awt.*;

public class Vehicle implements Animatable, Simulatable {

    public static final int STATE_RUNNING = 0;
    public static final int STATE_OUT_OF_SCENE = 2;
    public static final int STATE_WAITING = 1;

    private String id;
    private int runState;
    private int speed;
    private int posX;
    private int posY;
    private Road road;
    private int waitingTime;
    private int waitingInstances;

    public Vehicle(String id, Road road) {
        this.id = id;
        this.speed = 30;
        this.road = road;
        this.waitingTime = 0;
        this.waitingInstances = 0;
        this.posX = 0;
        this.posY = 0;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    private void moveAhead(){
        switch (this.getFacing()){
            case Road.DIRECTION_NORTH:
                this.posY--;
                break;

            case Road.DIRECTION_SOUTH:
                this.posY++;
                break;

            case Road.DIRECTION_EAST:
                this.posX++;
                break;

            case Road.DIRECTION_WEST:
                this.posX--;
                break;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D canvas = (Graphics2D) graphics;
        Image vehicle_image = null;
        try{
            switch (this.getFacing()){
                case Road.DIRECTION_NORTH:
                    vehicle_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.CAR_NORTH));
                    canvas.drawImage(vehicle_image, this.posX, this.posY, GraphicResources.VEHICLE_HEIGHT, GraphicResources.VEHICLE_WIDTH, null);
                    break;

                case Road.DIRECTION_SOUTH:
                    vehicle_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.CAR_SOUTH));
                    canvas.drawImage(vehicle_image, this.posX, this.posY, GraphicResources.VEHICLE_HEIGHT, GraphicResources.VEHICLE_WIDTH, null);
                    break;

                case Road.DIRECTION_EAST:
                    vehicle_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.CAR_EAST));
                    canvas.drawImage(vehicle_image, this.posX, this.posY, GraphicResources.VEHICLE_WIDTH, GraphicResources.VEHICLE_HEIGHT, null);
                    break;

                case Road.DIRECTION_WEST:
                    vehicle_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.CAR_WEST));
                    canvas.drawImage(vehicle_image, this.posX, this.posY, GraphicResources.VEHICLE_WIDTH, GraphicResources.VEHICLE_HEIGHT, null);
                    break;
            }
        }catch (Exception e){
            canvas.drawRect(this.posX, this.posY, GraphicResources.VEHICLE_WIDTH, GraphicResources.VEHICLE_HEIGHT);
        }
    }

    @Override
    public void init() {
        this.setRunState(STATE_RUNNING);
        this.speed = 30;
    }

    @Override
    public void simulate() {
        if (this.getRunState() == Vehicle.STATE_OUT_OF_SCENE) return;
        if (this.posX < 0 || this.posX > GraphicResources.BOUNDARY_X) this.setRunState(Vehicle.STATE_OUT_OF_SCENE);
        if (this.posY < 0 || this.posY > GraphicResources.BOUNDARY_Y) this.setRunState(Vehicle.STATE_OUT_OF_SCENE);
        int i = 0;
        while (i < speed){
            if (this.runState == Vehicle.STATE_RUNNING) {
                moveAhead();
            }
            road.check(this);
            i++;
        }
    }

    public int getRunState() {
        return runState;
    }

    public void setRunState(int runState) {
        this.runState = runState;
    }

    public void waitUntilGreen(){
        this.runState = Vehicle.STATE_WAITING;
        waitingInstances++;
    }

    public void goUntilRed(){
        this.runState = Vehicle.STATE_RUNNING;
    }

    public String getId() {
        return id;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getWaitingInstances() {
        return waitingInstances;
    }

    public int getAverageWaitingTime() {
        return waitingInstances == 0 ? 0 : (int) waitingTime/waitingInstances;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public int getFacing(){
        return this.road.getDirection();
    }
}
