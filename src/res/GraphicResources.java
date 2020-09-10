package res;

import java.awt.*;

public final class GraphicResources {

    private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public static final String CAR_NORTH = "/res/VehicleGraphics/red_car_north.png";
    public static final String CAR_SOUTH = "/res/VehicleGraphics/red_car_south.png";
    public static final String CAR_EAST = "/res/VehicleGraphics/red_car_east.png";
    public static final String CAR_WEST = "/res/VehicleGraphics/red_car_west.png";

    public static final String ROAD_HORIZONTAL = "/res/RoadGraphics/road_horizontal.jpg";
    public static final String ROAD_VERTICAL = "/res/RoadGraphics/road_vertical.jpg";

    public static final String SIGNAL_RED = "/res/SignalGraphics/signal_red.png";
    public static final String SIGNAL_GREEN = "/res/SignalGraphics/signal_green.png";

    public static final String INTERSECTION = "/res/IntersectionGraphics/intersection.png";

    public static final int VEHICLE_WIDTH = 80;
    public static final int VEHICLE_HEIGHT = 60;

    public static final int SIGNAL_WIDTH = 40;
    public static final int SIGNAL_HEIGHT = 120;

    public static final int ROAD_WIDTH = 160;
    public static final int ROAD_VERTICAL_LENGTH = (int) dimension.getHeight();
    public static final int ROAD_HORIZONTAL_LENGTH = (int) dimension.getWidth();

    private GraphicResources(){

    }
}
