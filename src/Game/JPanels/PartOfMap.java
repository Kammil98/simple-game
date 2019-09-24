/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.JPanels;

import Game.Frames.GameWindow;
import Avatars.Avatar;
import Avatars.Direction;
import Game.Listeners.PartOfMapResizeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kamil_2
 */
public abstract class PartOfMap extends javax.swing.JPanel implements BorderOwner{

    public long getLastDoorsUsage() {
        return lastDoorsUsage;
    }

    public void setLastDoorsUsage(long lastDoorsUsage) {
        this.lastDoorsUsage = lastDoorsUsage;
    }

    public long getDoorsCD() {
        return doorsCD;
    }

    protected void setDoorsCD(long doorsCD) {
        this.doorsCD = doorsCD;
    }

    public int getHorPartialBorderLength() {
        return horPartialBorderLength;
    }

    public void setHorPartialBorderLength(int upperPartialBorderLength) {
        this.horPartialBorderLength = upperPartialBorderLength;
    }

    public int getVertPartialBorderLength() {
        return vertPartialBorderLength;
    }

    public void setVertPartialBorderLength(int vertPartialBorderLength) {
        this.vertPartialBorderLength = vertPartialBorderLength;
    }

    public final Set<Avatar> getAvatarsOnMap() {
        return avatarsOnMap;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getVertBorderLength() {
        return vertBorderLength;
    }

    public void setVertBorderLength(int vertBorderLength) {
        this.vertBorderLength = vertBorderLength;
    }

    public int getSecondVertBorderY() {
        return secondVertBorderY;
    }

    public void setSecondVertBorderY(int secondVertBorderY) {
        this.secondVertBorderY = secondVertBorderY;
    }

    public int getRightBorderX() {
        return rightBorderX;
    }

    public void setRightBorderX(int rightBorderX) {
        this.rightBorderX = rightBorderX;
    }

    public int getSecondHorBorderX() {
        return secondHorBorderX;
    }

    public void setSecondHorBorderX(int secondUpperBorderX) {
        this.secondHorBorderX = secondUpperBorderX;
    }

    public int getHorBorderLength() {
        return horBorderLength;
    }

    public void setHorBorderLength(int horBorderLength) {
        this.horBorderLength = horBorderLength;
    }

    public int getBottomBorderY() {
        return bottomBorderY;
    }

    public void setBottomBorderY(int bottomBorderY) {
        this.bottomBorderY = bottomBorderY;
    }
    private long lastDoorsUsage, doorsCD;
    private volatile Set<Avatar> avatarsOnMap;
    private int borderWidth, vertPartialBorderLength, vertBorderLength, secondVertBorderY, rightBorderX,
            horPartialBorderLength, secondHorBorderX, horBorderLength,
            bottomBorderY;
    /**
     * Creates new form Arena
     */
    public PartOfMap() {
        initComponents();
        addComponentListener(new PartOfMapResizeListener());
        setBounds(0, 0, 1000, 700);
        avatarsOnMap = new HashSet<>();
        lastDoorsUsage = System.currentTimeMillis();
        doorsCD = 1500;
    }
    
    @Override
    public void calculateBorders(){
        Dimension dim = getParent().getSize();
        setBorderWidth(((dim.width + dim.height) / 170) + 1);
        setVertPartialBorderLength((dim.height * 6/14) + 1);
        setVertBorderLength(dim.height + 1);
        setSecondVertBorderY(dim.height * 8/14);
        setHorPartialBorderLength((dim.width * 9/20) + 1);
        setHorBorderLength(dim.width + 1);
        setSecondHorBorderX(dim.width * 11/20);
        setRightBorderX(dim.width - getBorderWidth());
        setBottomBorderY(dim.height - getBorderWidth());
    }
    public abstract PartOfMap getPlaceBehindDoors(Direction doors);
    private void drawBorder(Graphics2D g2d, boolean door, Rectangle2D rect1, Rectangle2D rect2, Rectangle2D rect3){
        if(door){
            g2d.fill(rect1);
            g2d.fill(rect2);
        }
        else{
            g2d.fill(rect3);
            g2d.fillRect(0, 0, getBorderWidth(), getVertPartialBorderLength());
        }
    }
    
    @Override
    public void drawBorders(Graphics2D g2d, boolean westDoor, boolean northDoor, boolean eastDoor, boolean southDoor){
        Rectangle2D rect1, rect2, rect3;
        g2d.setColor(Color.BLACK);
        //west border
        rect1 = new Rectangle2D.Double(0, 0, getBorderWidth(), getVertPartialBorderLength());
        rect2 = new Rectangle2D.Double(0, getSecondVertBorderY(), getBorderWidth(), getVertPartialBorderLength());
        rect3 = new Rectangle2D.Double(0, 0, getBorderWidth(), getVertBorderLength());
        drawBorder(g2d, westDoor, rect1, rect2, rect3);
        
        //east border
        rect1 = new Rectangle2D.Double(getRightBorderX(), 0, getBorderWidth(), getVertPartialBorderLength());
        rect2 = new Rectangle2D.Double(getRightBorderX(), getSecondVertBorderY(), getBorderWidth(), getVertPartialBorderLength());
        rect3 = new Rectangle2D.Double(getRightBorderX(), 0, getBorderWidth(), getVertBorderLength());
        drawBorder(g2d, eastDoor, rect1, rect2, rect3);
        
        //south border
        rect1 = new Rectangle2D.Double(0, getBottomBorderY(), getHorPartialBorderLength(), getBorderWidth());
        rect2 = new Rectangle2D.Double(getSecondHorBorderX(), getBottomBorderY(), getHorPartialBorderLength(), getBorderWidth());
        rect3 = new Rectangle2D.Double(0, getBottomBorderY(), getHorBorderLength(), getBorderWidth());
        drawBorder(g2d, southDoor, rect1, rect2, rect3);
        
        //north border
        rect1 = new Rectangle2D.Double(0, 0, getHorPartialBorderLength(), getBorderWidth());
        rect2 = new Rectangle2D.Double(getSecondHorBorderX(), 0, getHorPartialBorderLength(), getBorderWidth());
        rect3 = new Rectangle2D.Double(0,  0, getHorBorderLength(), getBorderWidth());
        drawBorder(g2d, northDoor, rect1, rect2, rect3);
    }
    protected void paintComponent(Graphics g, Color color) {
        super.paintComponent(g);
        Set<Avatar> deadAvatars = new HashSet<>();
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rect = GameWindow.getBasicCDGraphic();
        g2d.setColor(color);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        g2d.setColor(g2d.getBackground());
        if( GameWindow.myHero.getHp() > 0 ){
            GameWindow.myHero.drawAvatar(g2d);
            g2d.setColor(Color.YELLOW);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            //5 in X position is for better visual presentation. 5 in Y position is because of space between two baselines
            g2d.drawString("MONEY:", (float)rect.getMaxX() + 5, (float)rect.getY() + g2d.getFont().getSize() - 5);
            g2d.drawString(String.valueOf(GameWindow.myHero.getMoney()), (float)rect.getMaxX() + 5, (float)rect.getY() + 2 * g2d.getFont().getSize() - 5);
            GameWindow.drawCooldownBlocks(g2d);
        }
        getAvatarsOnMap().forEach((Avatar avatar) -> {
            if(avatar.getHp() > 0 )
                avatar.drawAvatar(g2d);
            else
                deadAvatars.add(avatar);
        });
        getAvatarsOnMap().removeAll(deadAvatars);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
