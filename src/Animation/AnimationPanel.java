package Animation;

import SimulationToolbox.ScenarioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationPanel extends JPanel implements ActionListener {

    private Timer timer = new Timer(100, this);
    private ScenarioHandler handler;

    public AnimationPanel(ScenarioHandler handler){
        this.handler = handler;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        handler.drawAnimatables(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handler.proceedSimulation();
    }
}
