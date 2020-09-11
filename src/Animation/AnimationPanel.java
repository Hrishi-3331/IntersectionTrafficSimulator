package Animation;

import SimulationToolbox.ScenarioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationPanel extends JPanel implements ActionListener {
    private Timer timer;
    private ScenarioHandler handler;

    AnimationPanel(ScenarioHandler handler){
        this.handler = handler;
        timer = new Timer(50, this);
    }

    void start(){
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.green);
        handler.drawAnimatables(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handler.proceedSimulation();
        if (handler.isTerminated()){
            timer.stop();
            System.out.println("Animation Ended");
        }
        this.repaint();
    }
}
