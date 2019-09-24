/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Avatars.Avatar;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.util.Set;

/**
 *
 * @author kamil_2
 */
public abstract class ResizeListener implements ComponentListener{
    
    private Dimension oldSize;
    
    public ResizeListener(){
        oldSize = new Dimension(1000,700);
    }
    public Dimension getOldSize() {
        return oldSize;
    }

    public void setOldSize(Dimension oldSize) {
        this.oldSize = oldSize;
    }
    public void resizeSet(Set<? extends Avatar> set, Dimension newSize){
        double scaleX = (double) newSize.width / (double) getOldSize().width;
        double scaleY = (double) newSize.height / (double) getOldSize().height;
        set.forEach((Avatar avatar) -> {
            avatar.rescaleAvatar(newSize, scaleX, scaleY);
        });
    }
    public void setBasicCDGraphic(){
        
    }

    
}
