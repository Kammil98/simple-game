/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Avatars.Avatar;
import Game.JPanels.BorderOwner;
import Game.JPanels.PartOfMap;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

/**
 *
 * @author kamil_2
 */
public class PartOfMapResizeListener extends ResizeListener {

    @Override
    public void componentResized(ComponentEvent ce) {
        Component component = ce.getComponent();
        Dimension newSize = component.getSize();
        ((BorderOwner) component).calculateBorders();
        
        resizeSet( ((PartOfMap) component).getAvatarsOnMap(),
                newSize);
        setOldSize(newSize);
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
        ((BorderOwner) ce.getComponent()).calculateBorders();
        Component component = ce.getComponent();
        long now = System.currentTimeMillis();
        ((PartOfMap) component).getAvatarsOnMap().forEach((object) -> {
            object.setLastMoveTime(now);
        });
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }
    
}
