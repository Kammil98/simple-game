/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import Game.AnimationDrawer;
import Game.Frames.GameWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamil_2
 */
public abstract class Avatar{

    public String getSound() {
        return sound;
    }

    public final void setSound(String sound) {
        this.sound = sound;
    }
    public int getLvl() {
        return lvl;
    }

    protected void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getReqExp() {
        return reqExp;
    }

    private void setReqExp(int reqExp) {
        this.reqExp = reqExp;
    }

    public int getExpForKill() {
        return expForKill;
    }

    private void setExpForKill(int expForKill) {
        this.expForKill = expForKill;
    }

    public Dimension getCurrJpanelSize() {
        return currJpanelSize;
    }

    private void setCurrJpanelSize(Dimension currJpanelSize) {
        currJpanelSize.height = currJpanelSize.height - 39; // size lost for menBar
        currJpanelSize.width = currJpanelSize.width - 16; // size lost for "invisible" part of Frame
        this.currJpanelSize = currJpanelSize;
    }

    public Object getMoneySynchronizer() {
        return moneySynchronizer;
    }

    public float getMoney() {
        synchronized(moneySynchronizer){
            return money;
        }
    }

    protected final void setMoney(float money) {
        synchronized(moneySynchronizer){
            this.money = money;
        }
    }

    /**
     *
     * @param receiver avatar, who will receive money
     * @param amount amount of given money
     * @return true if transaction is possible, false in other case
     */
    public boolean payMoney(Avatar receiver, float amount){
        float newBallance = getMoney() - amount;
        if(newBallance >= 0){
            setMoney(newBallance);
            receiver.setMoney(receiver.getMoney() + amount);
            return true;
        }
        else{
            return false;
        }
    }
    public Dimension getBodybounds() {
        return bodybounds;
    }

    public void setBodybounds(Dimension bodybounds) {
        this.bodybounds = bodybounds;
    }

    public boolean isNpc() {
        return npc;
    }

    public void setNpc(boolean npc) {
        this.npc = npc;
    }

    public double getRange() {
        return range;
    }

    public final void setRange(double range) {
        this.range = range;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public final void setMaxHp(int Maxhp) {
        if(getHp() > Maxhp)
            setHp(Maxhp);
        this.maxHp = Maxhp;
    }
    
    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }
    public double getSpeed() {
        return speed;
    }

    public final void setSpeed(double speed) {
        this.speed = speed;
    }
    public Set<Avatar> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Avatar> enemies) {
        this.enemies = enemies;
    }

    public long getLastAtk() {
        return lastAtkTime;
    }

    public void setLastAtk(long lastAtk) {
        this.lastAtkTime = lastAtk;
    }

    public long getBasicAtkCd() {
        return basicAtkCd;
    }

    public final void setBasicAtkCd(long basicAtkCd) {
        this.basicAtkCd = basicAtkCd;
    }

    public double getX() {
        return x;
    }

    private void setX(double X) {
        Dimension dim = getCurrJpanelSize();
        double borderMapWidth = ((double)(dim.width + dim.height) / 170) + 1;
        double leftBorder = borderMapWidth + getBodybounds().width / 2, 
                rightBorder = dim.width - borderMapWidth - getBodybounds().width / 2;
        if(X < leftBorder)
            this.x = leftBorder;
        else if (X > rightBorder)
            this.x = rightBorder;
        else {
            this.x = X;
        }
    }

    public double getY() {
        return y;
    }

    private void setY(double Y) {
        Dimension dim = getCurrJpanelSize();
        double borderMapWidth = ((double)(dim.width + dim.height) / 170) + 1;
        double upperBorder = borderMapWidth + getBodybounds().height / 2, 
                bottomBorder = dim.height - borderMapWidth - getBodybounds().height / 2;
        if(Y < upperBorder)
            this.y = upperBorder;
        else if (Y > bottomBorder)
            this.y = bottomBorder;
        else 
            this.y = Y;
        
    }
    public Point2D getLocation(){
        return new Point2D.Double(getX(), getY());
    }
    public final void increaseHp(int hp) {
        synchronized(HPSynchronizer){
            int newHp = getHp() + hp;
            if(newHp < 0)
                setHp(0);
            else
                setHp(newHp);
            System.out.println(getNickname() + "'s life decreased to " + getHp());
        }
        
    }
    
    public int getHp() {
        synchronized(HPSynchronizer){
            return hp;
        }
    }

    public final void setHp(int hp) {
        synchronized(HPSynchronizer){
            if(hp < 0)
                this.hp = 0;
            else{ 
                if(hp > getMaxHp())
                    this.hp = getMaxHp();
                else
                    this.hp = hp;
            }
        }
    }

    public String getNickname() {
        return nickname;
    }

    protected final void setNickname(String name) {
        this.nickname = name;
    }

    public int getAtk() {
        return atk;
    }

    protected final void setAtk(int atk) {
    if(atk < 1)
            atk = 1;
        this.atk = atk;
    }
    
    public int getDef() {
        return def;
    }
    
    protected final void setDef(int def) {
        if(def < 0)
            def = 0;
        this.def = def;
    }
    public final void setPosition(double newX, double newY) {
        setX(newX);
        setY(newY);
    }
    public double getDistance(Point2D point){
        double distX = (point.getX() - getX()), distY = (point.getY() - getY());
        return sqrt(distX*distX + distY*distY);
    }
    protected Avatar getFirstEnemy(){
        Iterator itr = getEnemies().iterator();
        if(itr.hasNext()){
            return (Avatar) itr.next();
        }
        else return null;
    }
    protected double getHPBarWidth(){
        return 50 * ((double)getHp()/(double)getMaxHp());
    }
    protected void drawHPBar(Graphics2D g2d){
        Dimension dim = getCurrJpanelSize();
        double barWidth = getHPBarWidth();
        g2d.fillRect( (int)(getX() - (barWidth/2 * (double)dim.width / 1000) ), 
                (int)(getY() - getBodybounds().height * 0.7), 
                (int)((barWidth) * (double)dim.width / 1000), 
                (int)(5 * (double)dim.height / 700) );//hpBar
    }
    public void attack(Avatar enemy){
        long currTime = System.currentTimeMillis();
        if(enemy.getHp() > 0 && getHp() > 0 && currTime - getLastAtk() > getBasicAtkCd()){
            synchronized(enemy){
                System.out.println(getSound());
                System.out.print(getNickname() + " attacked " + enemy.getNickname() + ". ");
                enemy.increaseHp(-Integer.max(1, getAtk() - enemy.def));
                if(enemy.getHp() <= 0){
                    enemy.payMoney(this, enemy.getMoney());
                    setExp(getExp() + enemy.getExpForKill());
                    if(getExp() >= getReqExp())
                        lvlUp();
                }
            }
            setLastAtk(currTime);
        }
    }
    public void moveMainHero(Point2D point){
        double distance = sqrt(getCurrJpanelSize().width * getCurrJpanelSize().height) / 15;
        setX(this.x + point.getX());
        setY(this.y + point.getY());
        Point2D doorLoc = new Point2D.Double(0, getCurrJpanelSize().height / 2);
        if(getDistance(doorLoc) < distance)
            GameWindow.useDoors(Direction.LEFT);
        doorLoc.setLocation(getCurrJpanelSize().width, getCurrJpanelSize().height / 2);
        if(getDistance(doorLoc) < distance)
            GameWindow.useDoors(Direction.RIGHT);
        doorLoc.setLocation(getCurrJpanelSize().width / 2, 0);
        if(getDistance(doorLoc) < distance)
            GameWindow.useDoors(Direction.UP);
        doorLoc.setLocation(getCurrJpanelSize().width / 2, getCurrJpanelSize().height);
        if(getDistance(doorLoc) < distance)
            GameWindow.useDoors(Direction.DOWN);
    }
    public void moveToHero(Avatar hero) {
        double distX = hero.getX() - getX(), distY = hero.getY() - getY(),
         dist = getDistance(hero.getLocation()), currTime = (double)System.currentTimeMillis();
        double normDistX = distX/dist, normDistY = distY/dist, 
                road = getSpeed() * (currTime - (double)getLastMoveTime());
        if(dist > getRange()){
            setPosition(getX() +  normDistX * road, getY() + normDistY * road );
        }
        setLastMoveTime((long) currTime);
    }
    protected abstract void showAvatar(Graphics2D g2d);

    /**
     * prepare avatar to new size of Component. 
     * Scale size of draw, range, speed and position
     * @param newSize new size of component
     * @param scaleX scale of width between old and new Size of Component
     * @param scaleY scale of height between old and new Size of Component
     */
    public void rescaleAvatar(Dimension newSize, double scaleX, double scaleY){
        setCurrJpanelSize(newSize);
        setPosition(getX() * scaleX, 
                getY() * scaleY);
        setRange(getRange() * scaleX * scaleY);
        setSpeed(getSpeed() * scaleX * scaleY);
    }
    public abstract void drawAvatar(Graphics2D g2d);
    public void drawAvatar(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        showAvatar(g2d);
    }
    public abstract void lvlUp();
    /**
     *
     * @param maxHPIncrease how much Hp limit should increase
     * @param atkIncrease how much atk should increase
     * @param defIncrease how much def should increase
     */
    public void lvlUp(int maxHPIncrease, int atkIncrease, int defIncrease){
        setLvl(getLvl() + 1);
        setExp(getExp() - getReqExp());
        setReqExp(getLvl() * 2);
        setExpForKill(getLvl());
        setMaxHp(getMaxHp() + 10);
        setHp(getMaxHp());
        setAtk(getAtk() + 2);
        setDef(getDef() + 1);
    }
    /**
     * Control CPU usage
     * @param loopTime time of loop where CPU usage 
     * is lowering(synchronizing with rendering)
     */
    protected void lowerCPUUsage(int loopTime){
        try {
            Thread.sleep(1000 / AnimationDrawer.getFPS() - loopTime);
        } catch (ArithmeticException ex){
            try {
                Thread.sleep(25 - loopTime);
            } catch (InterruptedException ex1) {
                Logger.getLogger(Avatar.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void interact();
//    @Override
//    public abstract void run();
    private String sound;
    private final Object moneySynchronizer;
    private final Object HPSynchronizer;
    private float money;
    private boolean npc;
    private int lvl;
    private int exp;
    private int reqExp;
    private int expForKill;
    private volatile double x;
    private volatile double y;
    private double range;
    private volatile int hp;
    private int maxHp;
    private Dimension bodybounds;
    private Dimension currJpanelSize;
    private double speed;
    private String nickname;
    private int atk;
    private long basicAtkCd;
    private long lastAtkTime;
    private long lastMoveTime;
    private int def;
    private Set<Avatar> enemies;
    public Avatar(Dimension containerSize){
        lvl = 1;
        exp = 0;
        reqExp = lvl * 2;
        expForKill = lvl;
        moneySynchronizer = new Object();
        HPSynchronizer = new Object();
        Random generator = new Random();
        setNickname("None");
        money = 0;
        hp = 100;
        maxHp = 100;
        bodybounds = new Dimension(1, 1);
        currJpanelSize = containerSize;
        range = 50;
        setDef(0);
        setAtk(1);
        basicAtkCd = 1000;
        speed = 1;
        lastMoveTime = lastAtkTime = System.currentTimeMillis();
        lastAtkTime -= (basicAtkCd + 1); // to be able, to attack instantly
        npc = true;
        setX(abs(generator.nextInt() % 968));
        setY(abs(generator.nextInt() % 636));
    }

    
    
}
