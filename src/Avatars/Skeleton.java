/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author kamil_2
 */
public class Skeleton extends Humanoidal implements Aggresive{

    public Line2D[] getBones() {
        return bones;
    }

    public void setBones(Line2D[] bones) {
        this.bones = bones;
    }
    
    private Line2D[] bones;
    public Skeleton(Dimension containerSize){
        super(containerSize);
        setNickname("Skeleton");
        setSound("Kle, kle");
        setMoney(abs(new Random().nextInt() % 10 + 1));
        setAtk(10);
        setRange(40);
        setBasicAtkCd(600);
        setDef(2);
        setHp(20);
        setMaxHp(20);
        setSpeed(0.03);
        bones = new Line2D[5];
        createBody();
    }
    @Override
    public final void createBody(){
        super.createBody();
        Line2D[] tmpBones;
        Dimension dim = getCurrJpanelSize();
        tmpBones = getBones();
        double currX = getX(), currY = getY(), 
                yWindowResize = (double)dim.height / 700;
        double stomachLength = 30  * yWindowResize, bonesWidth = 16 * yWindowResize;
        for(int i = 0; i < 5; i++){
            tmpBones[i] = new Line2D.Double(currX - bonesWidth / 2, 
                    currY - (stomachLength / 2) +(stomachLength / 5 * (i+1) ), 
                    currX + bonesWidth / 2, 
                    currY - stomachLength / 2 +(stomachLength / 5 * (i+1)));
        }
        setBones(tmpBones);
    }
    @Override
    public void drawAvatar(Graphics2D g2d) {
        super.drawAvatar(g2d, Color.RED);
    }
    @Override
    protected void showAvatar(Graphics2D g2d) {
        super.showAvatar(g2d);
        for(Line2D bone: getBones())
            g2d.draw(bone);
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
        super.lvlUp(5, 2, 0);
    }
    

}
