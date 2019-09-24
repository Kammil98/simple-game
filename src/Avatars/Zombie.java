/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author kamil_2
 */
public class Zombie extends Humanoidal implements Aggresive{

    public Rectangle2D getBigStomach() {
        return bigStomach;
    }

    public void setBigStomach(Rectangle2D bigStomach) {
        this.bigStomach = bigStomach;
    }
    
    public Zombie(Dimension containerSize){
        super(containerSize);
        setNickname("Zombie");
        setSound("Arrgh");
        setAtk(15);
        setDef(2);
        setHp(50);
        setMaxHp(50);
        setMoney(abs(new Random().nextInt() % 10 + 6));
        setRange(40);
        setBasicAtkCd(2000);
        setSpeed(0.02);
        createBody();
    }
    private Rectangle2D bigStomach;
    
    @Override
    public void createBody(){
        super.createBody();
        Dimension dim = getCurrJpanelSize();
        double currX = getX(), currY = getY(), 
                xWindowResize = (double)dim.width / 1000, yWindowResize = (double)dim.height / 700;
        double stomachLength = 30  * yWindowResize, stomachWidth = 14 * xWindowResize;
         setBigStomach(new Rectangle2D.Double(currX - stomachWidth / 2, currY - stomachLength / 2, stomachWidth, stomachLength) );
    }
    @Override
    public void drawAvatar(Graphics2D g2d) {
        super.drawAvatar(g2d, Color.GREEN);
    }

    @Override
    protected void showAvatar(Graphics2D g2d) {
        super.showAvatar(g2d);
        g2d.fill(getBigStomach());
    }
    @Override
    public void interact(){
        Avatar hero = getFirstEnemy();
        if(hero != null && getDistance(hero.getLocation()) < getRange()){
            attack(hero);
        }
        if( hero != null )
            moveToHero(hero);
    }

    
    @Override
    public void lvlUp() {
        super.lvlUp(10, 1, 1);
    }
}
