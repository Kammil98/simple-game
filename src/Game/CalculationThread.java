/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Avatars.Avatar;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author kamil_2
 */
public class CalculationThread extends SwingWorker<Void, Void>{

    public Iterator<Avatar> getIter() {
        return iter;
    }
    public CalculationThread(Iterator<Avatar> iiter){
        this.iter = iiter;
//        time = System.currentTimeMillis();
    }
    private long time;
    private final Iterator<Avatar> iter;

    @Override
    protected Void doInBackground() throws Exception {
        getIter().forEachRemaining(avatar ->{
            avatar.interact();
        });
        return null;
    }
    @Override
    protected void done(){
//        time = System.currentTimeMillis() - time;
//        System.out.println("Time of Calculations = " + time);
    }
    
    
}
