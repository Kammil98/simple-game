/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kamil_2
 */
public class SkeletonTest {
    Skeleton instance;
    Avatar enemy;
    public SkeletonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        enemy = new Human(new Dimension(700, 800));
        instance = new Skeleton(new Dimension(700, 800));
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of moveToHero method, of class Skeleton.
     */
    @Test
    public void testMoveToHero() {
        System.out.println("moveToHero");
        instance.setPosition(300, 200);
        enemy.setPosition(100, 100);
        double previousDist = instance.getDistance(enemy.getLocation());
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(SkeletonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.moveToHero(enemy);
        assertTrue(instance.getDistance(enemy.getLocation()) < previousDist);
    }
    
}
