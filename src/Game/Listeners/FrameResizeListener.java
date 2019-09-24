/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Game.Frames.GameWindow;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author kamil_2
 */
public class FrameResizeListener extends ResizeListener{

    public void resizeBasciCDGraphic(Dimension newSize){
        int borderWidth = ((newSize.width + newSize.height) / 170) + 1;
        double scale = Math.sqrt((double)(newSize.width * newSize.height) 
                / (double)(getOldSize().width * getOldSize().height));
        Rectangle2D oldGraphic = GameWindow.getBasicCDGraphic();
        GameWindow.setBasicCDGraphic(new Rectangle2D.Double(
                borderWidth,
                newSize.height - borderWidth - oldGraphic.getHeight() * scale,
                oldGraphic.getWidth() * scale,
                oldGraphic.getHeight() * scale
        ));
        setOldSize(newSize);
    }
    @Override
    public void componentResized(ComponentEvent ce) {
        Component component =  ce.getComponent();
        Dimension newSize = component.getSize();
        resizeSet(GameWindow.heroes, newSize);
        
        ((GameWindow)component).getActivePanel().setBounds(0, 0, newSize.width, newSize.height);
        resizeBasciCDGraphic(newSize);
    }
    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }
    
}
