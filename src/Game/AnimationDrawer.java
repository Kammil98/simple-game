/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.JPanels.PartOfMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author kamil_2
 */
public class AnimationDrawer implements ActionListener{
    
    public PartOfMap getPanel() {
        return panel;
    }
    public void setPanel(PartOfMap panel) {
        this.panel = panel;
    }

    /**
     *
     * @param panel
     * @param FPS
     */
    public AnimationDrawer(PartOfMap panel, int FPS){
        this.FPS = FPS;
        this.panel = panel;
        timer = new Timer(1000 / FPS, this);
        timer.setInitialDelay(0);
        timer.start();
//        time = System.currentTimeMillis();
        worker = new CalculationThread(getPanel().getAvatarsOnMap().iterator());
        worker.execute();
    }
    public static int getFPS() {
        return FPS;
    }

    public static void setFPS(int aFPS) {
        FPS = aFPS;
    }
    private CalculationThread worker;
    private long time;
    private final Timer timer;
    private PartOfMap panel;
    private static int FPS;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!worker.isDone())
            return;
        getPanel().repaint();
//        time = System.currentTimeMillis() - time;
//        System.out.println("Time of drawing = " + time);
//        time = System.currentTimeMillis();
        worker = new CalculationThread(getPanel().getAvatarsOnMap().iterator());
        worker.execute(); // for now longer than normal calculations
    }
    
}
