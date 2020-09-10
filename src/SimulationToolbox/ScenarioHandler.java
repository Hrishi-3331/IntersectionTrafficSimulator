package SimulationToolbox;

import Animation.Animatable;

import java.awt.*;
import java.util.ArrayList;

public class ScenarioHandler {

    private Scenario scenario;

    public ScenarioHandler(Scenario scenario){
        this.scenario = scenario;
    }

    public void proceedSimulation(){

    }

    public void drawAnimatables(Graphics graphics){
        ArrayList<Animatable> animatables = scenario.getAnimatables();
        for (int i = 0; i < animatables.size(); i++){
            Animatable obj = animatables.get(i);
            obj.draw(graphics);
        }
    }

}
