/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author kamil_2
 */
public class Human extends Humanoidal {

    
    public Human(Dimension containerSize){
        super(containerSize);
        setNickname("Human");
        setSound("Meh");
        setAtk(15);
        setDef(5);
        setPosition(40, 100);
        createBody();
    }
    
    public final void createBody(){
        super.createBody();
    }
    
    public void drawAvatar(Graphics2D g2d){
        super.drawAvatar(g2d, Color.BLACK);
        
    }
    @Override
    public void showAvatar(Graphics2D g2d) {
        super.showAvatar(g2d);
    }
    
    public void lvlUp(){
        super.lvlUp(10, 2, 1);
    }

    @Override
    public void interact() {
        
    }

}
