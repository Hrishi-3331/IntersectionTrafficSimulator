import Intersection.Intersection;
import Road.OneWayRoad;
import Road.Road;
import SignalController.SignalController;
import SimulationToolbox.Scenario;
import SimulationToolbox.ScenarioHandler;
import TrafficSignal.TrafficSignal;

import java.util.ArrayList;

public class Main extends Scenario {

    public static void main(String[] args) {
        Scenario scenario = new Main();
        ScenarioHandler handler = new ScenarioHandler(scenario, ScenarioHandler.DURATION_INFINITE);
        handler.runSimulation();
    }

    @Override
    public void buildScenario() {
        Road road = new OneWayRoad("Road1", Road.DIRECTION_WEST);
        Road road1 = new OneWayRoad("Road2", Road.DIRECTION_NORTH);
        Intersection intersection = new Intersection("Intersection1");
        intersection.addRoad(road);
        intersection.addRoad(road1);
        TrafficSignal signal = new TrafficSignal(road);
        TrafficSignal signal1 = new TrafficSignal(road1);
        SimpleSignalController controller = new SimpleSignalController(intersection);
        intersection.setSignalController(controller);
        addComponent(road);
        addComponent(road1);
        addComponent(signal);
        addComponent(signal1);
        addComponent(intersection);
        addComponent(controller);
    }

    public class SimpleSignalController extends SignalController{
        int flag;
        int timeout;
        boolean toggle;
        ArrayList<TrafficSignal> signals;

        public SimpleSignalController(Intersection intersection) {
            flag = 0;
            timeout = 15;
            signals = new ArrayList<TrafficSignal>();
            this.setIntersection(intersection);
            ArrayList<Road> list = this.intersection.getRoads();
            for (Road road : list){
                signals.add(road.getTrafficSignal());
            }
            toggle = false;
        }

        @Override
        public void init() {
            signals.get(0).setGreen();
        }

        @Override
        public void simulate() {
            flag++;
            if (flag == timeout){
                for (TrafficSignal signal : signals) signal.toggleState();
                flag = 0;
                toggle = !toggle;
                if (toggle) timeout = 8;
                else timeout = 35;
            }
        }
    }
}
