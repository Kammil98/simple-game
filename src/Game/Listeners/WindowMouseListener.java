/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Avatars.Aggresive;
import Avatars.Avatar;
import Game.Frames.GameWindow;
import static Game.Frames.GameWindow.myHero;
import Game.JPanels.PartOfMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

/**
 * Control attacks of Hero
 * @author kamil_2
 */
public class WindowMouseListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Iterator iter = ((PartOfMap) GameWindow.getActivePanel()).getAvatarsOnMap().iterator();
        Avatar avatar;
        Avatar firstEnemy = null;
        boolean attacked = false;
        while(iter.hasNext() && ! attacked){
            avatar = (Avatar) iter.next();
            if(avatar instanceof Aggresive) 
                firstEnemy = avatar;
            if(firstEnemy != null && firstEnemy.getDistance(myHero.getLocation()) < myHero.getRange()){
                myHero.attack(firstEnemy);
                attacked = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
