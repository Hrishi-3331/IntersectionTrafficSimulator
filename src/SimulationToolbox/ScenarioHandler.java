package SimulationToolbox;

import Animation.Animatable;
import Animation.AnimationWindow;
import Intersection.Intersection;
import Road.Road;
import Vehicle.Vehicle;

import java.awt.*;
import java.util.ArrayList;

public class ScenarioHandler {

    public static final double DURATION_DEFAULT = 300;
    public static final double DURATION_INFINITE = Double.MAX_VALUE;

    private Scenario scenario;
    private double instance;
    private double duration;
    private int[] flags;
    private ArrayList<Road> roads;

    public ScenarioHandler(Scenario scenario){
        this.scenario = scenario;
        instance = 0;
        duration = DURATION_DEFAULT;
    }

    public ScenarioHandler(Scenario scenario, double duration){
        this(scenario);
        this.duration = duration;
    }

    public void proceedSimulation(){
        instance++;
        ArrayList<Simulatable> simulatables = scenario.getSimulatables();
        for (int i = 0; i < simulatables.size(); i++){
            Simulatable obj = simulatables.get(i);
            obj.simulate();
        }
        for(int i = 0; i < flags.length; i++){
            flags[i]++;
            if (roads.get(i).newVehicle(flags[i], scenario)){
                flags[i] = 0;
            }
        }
    }

    public void drawAnimatables(Graphics graphics){
        ArrayList<Animatable> animatables = scenario.getAnimatables();
        for (int i = 0; i < animatables.size(); i++){
            Animatable obj = animatables.get(i);
            obj.draw(graphics);
        }
    }

    public boolean isTerminated(){
        return (instance >= duration);
    }

    public double getCurrentInstance(){
        return this.instance;
    }

    public void runSimulation(){
        scenario.buildScenario();
        AnimationWindow window = new AnimationWindow(this);
        ArrayList<Simulatable> simulatables = scenario.getSimulatables();
        for (int i = 0; i < simulatables.size(); i++){
            Simulatable obj = simulatables.get(i);
            obj.init();
        }
        Intersection intersection = scenario.getIntersection();
        roads = intersection.getRoads();
        flags = new int[roads.size()];
        for(int i = 0; i < roads.size(); i++){
            flags[i] = 0;
        }
        window.start();
        if (this.getCurrentInstance() % 30 == 0){
            cleanup();
        }
    }

    private void cleanup() {
        ArrayList<Simulatable> list1 = scenario.getSimulatables();
        ArrayList<Animatable> list2 = scenario.getAnimatables();
        ArrayList<Object> trash = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++){
            Simulatable o = list1.get(i);
            if (o instanceof Vehicle && ((Vehicle)o).getRunState() == Vehicle.STATE_OUT_OF_SCENE){
                trash.add(o);
            }
        }

        for (int i = 0; i < trash.size(); i++){
            list1.remove((Simulatable) trash.get(i));
            list2.remove((Animatable) trash.get(i));
        }
        trash.clear();
    }
}
