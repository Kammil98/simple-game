/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostagra;

import Game.CalculationThread;
import Game.Frames.GameWindow;

/**
 *
 * @author kamil_2
 */
public class ProstaGra {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println(java.lang.Thread.activeCount());
//        System.out.println(java.lang.Thread.currentThread().getName());
        GameWindow.main(args);
        //new Thread(new CalculationThread()).start();
    }
    
}
