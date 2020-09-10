package SimulationToolbox;

import Animation.Animatable;
import java.util.ArrayList;

public abstract class Scenario {

    private ArrayList<Simulatable> simulatables;
    private ArrayList<Animatable> animatables;

    public Scenario() {
        simulatables = new ArrayList<>();
        animatables = new ArrayList<>();
    }

    public abstract void buildScenario();

    public void addComponent(Object o){
        if (o instanceof Simulatable){
            simulatables.add((Simulatable)o);
        }
        if (o instanceof Animatable){
            animatables.add((Animatable)o);
        }
    }

    public void runScenario(){

    }

    public ArrayList<Simulatable> getSimulatables() {
        return simulatables;
    }

    public ArrayList<Animatable> getAnimatables() {
        return animatables;
    }
}
