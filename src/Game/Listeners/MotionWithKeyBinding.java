/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Listeners;

import Game.Frames.GameWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;



/**
 *
 * @author kamil_2
 */
public class MotionWithKeyBinding implements ActionListener{ 

    public Map<String, Point2D> getPressedKeys() {
        return pressedKeys;
    }
// we need ActionListener to catch timer events

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }
    
    private JComponent component;
    Timer timer;
    private final Map<String, Point2D> pressedKeys = new HashMap<>();
    
    /**
     *
     * @param component
     * @param delay delay in miliseconds between two moves of hero
     */
    public MotionWithKeyBinding(JComponent component, int delay){
        this.component = component;
        timer = new Timer(delay, this);
        timer.setInitialDelay(0); // delay before fire first event
    }
    public void changeComponent(JComponent component){
        InputMap inputMap = this.getComponent().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = this.getComponent().getActionMap();
        component.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        component.setActionMap(actionMap);
        setComponent(component);
    }
    public void addAction(String keyStroke, double deltax, double deltay){
        // divide keyStroke on kayName and modifiers  like SHIFT, etc.
        int offset = keyStroke.lastIndexOf(" ");
        //actually if isn't needed. For offset==-1 offset + 1 = 0*takes alle keyStroke.
        //Left it for make code clear
        String key = offset == -1 ? keyStroke : keyStroke.substring(offset + 1);
        String modifiers = keyStroke.replace(key, "");//remove keyName from keyStroke. Only modifiers with space stayed.
        
        //  Get the InputMap and ActionMap of the component
        InputMap inputMap = getComponent().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = getComponent().getActionMap();
        
        //  Create Action and prepare for binding for the pressed key
        Action pressedAction = new KeyBindingAction(this, key, new Point2D.Double(deltax, deltay) );//create Action
        String pressedKeyWithModifiers = modifiers + "pressed " + key;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKeyWithModifiers);//create KeyStroke With all modifiers
        //binding for the pressed key with InputMap and ActionMap
        inputMap.put(pressedKeyStroke, pressedKeyWithModifiers);
	actionMap.put(pressedKeyWithModifiers, pressedAction);
                
        //  Create Action and add binding for the released key
        Action releasedAction = new KeyBindingAction(this, key, null);//create Action, but null for deltaMotion
        String releasedKeyWithModifiers = modifiers + "released " + key;
        KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKeyWithModifiers);
        inputMap.put(releasedKeyStroke, releasedKeyWithModifiers);
        actionMap.put(releasedKeyWithModifiers, releasedAction);
    }
    
    //  Invoked whenever a key is pressed or released
    public void handleKeyEvent(String key, Point2D moveDelta){
            //  Keep track of which keys are pressed
            if (moveDelta == null)
                    getPressedKeys().remove(key);
            else
                    getPressedKeys().put(key, moveDelta);
            
            if (getPressedKeys().size() == 1)
                timer.start();
            if (getPressedKeys().isEmpty())
                timer.stop();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        double deltaX = 0, deltaY = 0;
        for (Point2D delta : getPressedKeys().values()){
                deltaX += delta.getX() * GameWindow.myHero.getSpeed();
                deltaY += delta.getY() * GameWindow.myHero.getSpeed();
        }
        GameWindow.myHero.moveMainHero(new Point2D.Double(deltaX, deltaY));
    }
    
}
