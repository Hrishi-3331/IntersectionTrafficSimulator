package TrafficSignal;

import Animation.Animatable;
import Road.Road;
import SignalController.SignalController;
import res.GraphicResources;

import javax.imageio.ImageIO;
import java.awt.*;

public class TrafficSignal implements Animatable {

    public static final int STATE_GREEN = 0;
    public static final int STATE_RED = 1;

    private int state;
    private Road road;
    private int posX;
    private int posY;
    private int restrictorPos;
    private SignalController controller;

    public TrafficSignal(int posX, int posY, int restrictorPos) {
        this.state = STATE_RED;
        this.posX = posX;
        this.posY = posY;
        this.restrictorPos = restrictorPos;
    }

    public void setController(SignalController controller){
        this.controller = controller;
    }

    public int getSignalState(){
        return this.state;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D canvas = (Graphics2D) graphics;
        Image signal_image;
        try{
            switch (this.getSignalState()){
                case TrafficSignal.STATE_GREEN:
                    signal_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.SIGNAL_GREEN));
                    canvas.drawImage(signal_image, posX, posY, GraphicResources.SIGNAL_WIDTH, GraphicResources.SIGNAL_HEIGHT, null);
                    break;

                case TrafficSignal.STATE_RED:
                    signal_image = ImageIO.read(getClass().getResourceAsStream(GraphicResources.SIGNAL_RED));
                    canvas.drawImage(signal_image, posX, posY, GraphicResources.SIGNAL_WIDTH, GraphicResources.SIGNAL_HEIGHT, null);
                    break;
            }
        }
        catch (Exception e){
            canvas.fillOval(this.posX, this.posY, 20, 20);
        }
    }

    public int getRestrictorPos() {
        return restrictorPos;
    }

    public SignalController getController() {
        return controller;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setRestrictorPos(int restrictorPos) {
        this.restrictorPos = restrictorPos;
    }

    public void toggleState(){
        if (this.getSignalState() == TrafficSignal.STATE_GREEN){
            this.setRed();
        }
        else this.setGreen();
    }

    public void setGreen(){
        this.state = TrafficSignal.STATE_GREEN;
    }

    public void setRed(){
        this.state = TrafficSignal.STATE_RED;
    }

}
