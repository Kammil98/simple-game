/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.JPanels;

import java.awt.Graphics2D;

/**
 *
 * @author kamil_2
 */
public interface BorderOwner {

    /**
     * draw Borders around map
     * @param g2d
     * @param northDoor
     * @param eastDoor
     * @param southDoor
     * @param westDoor
     */
    public void drawBorders(Graphics2D g2d, boolean northDoor, boolean eastDoor, boolean southDoor, boolean westDoor);
    public void calculateBorders();
}
