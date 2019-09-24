package Game.Frames;

import Avatars.Avatar;
import Avatars.Direction;
import Avatars.Human;
import Game.AnimationDrawer;
import Game.JPanels.Arena;
import Game.JPanels.City;
import Game.Listeners.FrameResizeListener;
import Game.Listeners.MotionWithKeyBinding;
import Game.Listeners.WindowMouseListener;
import Game.JPanels.PartOfMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kamil_2
 */
public class GameWindow extends javax.swing.JFrame{

    public static PartOfMap getActivePanel() {
        return activePanel;
    }

    public static void setActivePanel(PartOfMap newPanel) {
        if(activePanel != null)
            activePanel.setVisible(false);
        activePanel = newPanel;
        activePanel.setVisible(true);
        //CalculationThread.setIter(activePanel.getAvatarsOnMap().iterator());
        activePanel.setLastDoorsUsage(System.currentTimeMillis());
        if(motionWithKey != null){
            motionWithKey.changeComponent(activePanel);
        }
    }

    public static PartOfMap getArena() {
        return arena;
    }

    public static void setArena(PartOfMap aArena) {
        arena = aArena;
    }

    public static PartOfMap getCity() {
        return city;
    }

    public static void setCity(PartOfMap aCity) {
        city = aCity;
    }

    public static MotionWithKeyBinding getMotionWithKey() {
        return motionWithKey;
    }

    public static void setMotionWithKey(MotionWithKeyBinding aMotionWithKey) {
        motionWithKey = aMotionWithKey;
    }

    public static AnimationDrawer getDrawer() {
        return drawer;
    }

    public static void setDrawer(AnimationDrawer aDrawer) {
        drawer = aDrawer;
    }

    public static Rectangle2D getBasicCDGraphic() {
        return basicCDGraphic;
    }

    public static void setBasicCDGraphic(Rectangle2D aBasicCDGraphic) {
        basicCDGraphic = aBasicCDGraphic;
    }

    /**
     * Creates new form GameWindow
     */
    
    private static AnimationDrawer drawer;
    private static MotionWithKeyBinding motionWithKey;
    public static Human myHero;
    public static Set<Avatar> heroes = new HashSet<>(); // hero with his companions
    private static PartOfMap activePanel;
    private static PartOfMap arena;
    private static PartOfMap city;
    private static Rectangle2D basicCDGraphic;
    public GameWindow() {
        initComponents();
        addMouseListener(new WindowMouseListener());
        addComponentListener(new FrameResizeListener());
        prepareParameters();
        prepareHeroes();
        addMotions();
        pack();
        setSize(1000, 700);
    }
    private void addMotions(){
        motionWithKey = new MotionWithKeyBinding(getActivePanel(), 25);
        motionWithKey.addAction("shift A", -2, 0);
        motionWithKey.addAction("shift D", 2, 0); 
        motionWithKey.addAction("shift S", 0, 2);
        motionWithKey.addAction("shift W", 0, -2);
        motionWithKey.addAction("shift Q", 0, 0);
        motionWithKey.addAction("A", -1, 0);
        motionWithKey.addAction("D", 1, 0); 
        motionWithKey.addAction("S", 0, 1);
        motionWithKey.addAction("W", 0, -1);
        motionWithKey.addAction("Q", 0, 0);
    }
    private void prepareParameters(){
        setBasicCDGraphic(new Rectangle2D.Double(10, 610, 40, 40));
        setArena(new Arena());
        setCity(new City());
        add(getArena());
        add(getCity());
        setActivePanel(getArena());
        drawer  = new AnimationDrawer(getActivePanel(), 40);
    }
    private void prepareHeroes(){
        myHero = new Human(getActivePanel().getSize());
        myHero.setNpc(false);
        heroes.add(myHero);
    }
    
    public static void drawCooldownBlocks(Graphics2D g2d){
        long waitedTime = System.currentTimeMillis() - myHero.getLastAtk();
        g2d.setColor(new Color(0,0,200));
        g2d.fill(getBasicCDGraphic());
        g2d.setColor(new Color(128,128,128,128));
        g2d.fill(new Rectangle2D.Double(
                getBasicCDGraphic().getX(),
                getBasicCDGraphic().getY(),
                getBasicCDGraphic().getWidth(),
                getBasicCDGraphic().getHeight() * Double.min(1.0, (double)waitedTime/(double)myHero.getBasicAtkCd())
        ));
    }
    public static void useDoors(Direction dir){
        PartOfMap newActivePanel = getActivePanel().getPlaceBehindDoors(dir);
        if(newActivePanel == null)
            return;
        setActivePanel(newActivePanel);
        GameWindow.getDrawer().setPanel(getActivePanel());
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GameWindow().setVisible(true);
        });
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
