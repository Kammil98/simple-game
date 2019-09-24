/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author kamil_2
 */
public abstract class Humanoidal extends Avatar{

    public Ellipse2D getHead() {
        return head;
    }

    public void setHead(Ellipse2D head) {
        this.head = head;
    }

    public Line2D getStomach() {
        return stomach;
    }

    public void setStomach(Line2D stomach) {
        this.stomach = stomach;
    }

    public Line2D getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Line2D leftHand) {
        this.leftHand = leftHand;
    }

    public Line2D getRightHand() {
        return rightHand;
    }

    public void setRightHand(Line2D rightHand) {
        this.rightHand = rightHand;
    }

    public Line2D getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(Line2D leftLeg) {
        this.leftLeg = leftLeg;
    }

    public Line2D getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(Line2D rightLeg) {
        this.rightLeg = rightLeg;
    }
    
    private Ellipse2D head;
    private Line2D stomach;
    private Line2D leftHand;
    private Line2D rightHand;
    private Line2D leftLeg;
    private Line2D rightLeg;
    public Humanoidal(Dimension containerSize) {
        super(containerSize);
    }
    public void createBody(){
        Dimension dim = getCurrJpanelSize();
        double currX = getX(), currY = getY(), 
                xWindowResize = (double)dim.width / 1000, yWindowResize = (double)dim.height / 700;
        double handsRateX = 25 * xWindowResize, handsRateY = 20 * yWindowResize,
                legsRateX = 15 * xWindowResize, legsRateY = 20 * yWindowResize,
                handsPos = currY - 5  * yWindowResize, legsPos = currY + 15  * yWindowResize,
                stomachLength = 30  * yWindowResize, headRay = 20  * yWindowResize;
        
        setHead(new Ellipse2D.Double(currX - headRay / 2, currY - headRay - stomachLength / 2, headRay, headRay));
        setStomach(new Line2D.Double(currX, currY - stomachLength / 2, currX, currY + stomachLength / 2));
        setLeftHand(new Line2D.Double(currX, handsPos, currX - handsRateX, handsPos - handsRateY));
        setRightHand(new Line2D.Double(currX, handsPos, currX + handsRateX, handsPos - handsRateY));
        setLeftLeg(new Line2D.Double(currX, legsPos, currX - legsRateX, legsPos + legsRateY));
        setRightLeg(new Line2D.Double(currX, legsPos, currX + legsRateX, legsPos + legsRateY));
        
        setBodybounds(new Dimension((int)handsRateX * 2, (int)(legsRateX + stomachLength + headRay)));
    }
    
    @Override
    public void drawAvatar(Graphics2D g2d, Color color) {
        super.drawAvatar(g2d, color);
    }
    @Override
    protected void showAvatar(Graphics2D g2d) {
        createBody();
        Color color = g2d.getColor();
        g2d.draw(getHead());
        g2d.draw(new Ellipse2D.Float((int)getX()-2, (int)getY()-2, 4, 4)); // shows exact position of Human
        g2d.draw(getStomach());
        g2d.draw(getLeftHand());
        g2d.draw(getRightHand());
        g2d.draw(getLeftLeg());
        g2d.draw(getRightLeg());
        g2d.setColor(Color.RED);
        drawHPBar(g2d);
        g2d.setColor(color);
    }
}
