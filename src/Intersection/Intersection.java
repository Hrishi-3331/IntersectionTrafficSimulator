package Intersection;

import Animation.Animatable;
import Road.Road;
import SignalController.SignalController;
import SimulationToolbox.Simulatable;
import TrafficSignal.TrafficSignal;

import java.awt.*;
import java.util.ArrayList;

public class Intersection implements Animatable, Simulatable {

    private String id;
    private ArrayList<Road> roads;
    private ArrayList<TrafficSignal> signals;
    private SignalController signalController;

    public Intersection(String id) {
        this.id = id;
        roads = new ArrayList<Road>();
        signals = new ArrayList<TrafficSignal>();
    }

    public void addRoad(Road road){
        this.roads.add(road);
    }

    public void addSignal(TrafficSignal signal){
        this.signals.add(signal);
    }

    public SignalController getSignalController() {
        return signalController;
    }

    public void setSignalController(SignalController signalController) {
        this.signalController = signalController;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void init() {

    }

    @Override
    public void simulate() {

    }
}
