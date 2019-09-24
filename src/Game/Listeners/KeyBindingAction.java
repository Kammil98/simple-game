/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Game.Frames.Stats;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.AbstractAction;

/**
 *
 * @author kamil_2
 */
public class KeyBindingAction extends AbstractAction implements ActionListener{
    Point2D deltaMove;
    MotionWithKeyBinding handler;
    /**
     * Creates new Action. When Key on keyboard is pressed/released, 
     * then actionPerformed is activated.
     * @param handler
     * @param name name of key on keyboard
     * @param point X and Y  motion of hero
     */
    public KeyBindingAction(MotionWithKeyBinding handler, String name, Point2D point){
        super(name);
        this.deltaMove = point;
        this.handler = handler;
    }

    /**
     * Inform MotionWithKeyBinding, that key is pressed/released
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if("q".equals(ae.getActionCommand().toLowerCase())){
            Stats.main(null);
            handler.getPressedKeys().clear();
        }
        else{
            handler.handleKeyEvent((String)getValue(NAME), deltaMove);
        }
        
    }
}
