import Intersection.Intersection;
import Road.OneWayRoad;
import Road.Road;
import SimulationToolbox.Scenario;
import SimulationToolbox.ScenarioHandler;

public class Main extends Scenario {

    public static void main(String[] args) {
        Scenario scenario = new Main();
        ScenarioHandler handler = new ScenarioHandler(scenario, ScenarioHandler.DURATION_INFINITE);
        handler.runSimulation();
    }

    @Override
    public void buildScenario() {
        Road road = new OneWayRoad("Road1", Road.DIRECTION_EAST);
        Road road1 = new OneWayRoad("Road2", Road.DIRECTION_NORTH);
        Intersection intersection = new Intersection("Intersection1");
        intersection.addRoad(road);
        intersection.addRoad(road1);
        addComponent(road);
        addComponent(road1);
        addComponent(intersection);
    }
}
