/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avatars;

import java.awt.Dimension;
import java.awt.Graphics2D;
import static java.lang.Integer.max;
import java.util.HashSet;
import java.util.Set;
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
public class AvatarTest {
    
    Avatar enemy, instance;
    public AvatarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        enemy = new AvatarImpl(new Dimension(700, 800));
        instance = new AvatarImpl(new Dimension(700, 800));
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getDistance method, of class Avatar.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        enemy.setPosition(100, 200);
        instance.setPosition(400, 600);
        double expResult = 500;
        double result = instance.getDistance(enemy.getLocation());
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFirstEnemy method, of class Avatar.
     */
    @Test
    public void testGetFirstEnemy() {
        System.out.println("getFirstEnemy");
        Set<Avatar>enemies = new HashSet<>();
        enemies.add(enemy);
        instance.setEnemies(enemies);
        Avatar expResult = enemy;
        Avatar result = instance.getFirstEnemy();
        assertEquals(expResult, result);
    }

    /**
     * Test of attack method, of class Avatar.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        enemy.attack(instance);
        int expResult = instance.getMaxHp() - max(1,  instance.getDef() - enemy.getAtk());
        int result = instance.getHp();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of lvlUp method, of class Avatar.
     */
    @Test
    public void testLvlUp() {
        System.out.println("lvlUp");
        instance.setExp(instance.getReqExp());
        int oldExpForKill = instance.getExpForKill();
        instance.lvlUp();
        int expResult1 = 2;
        int result1 = instance.getLvl();
        int result2 = instance.getExp();
        assertEquals(expResult1, result1);
        assertTrue(result2 >= 0);
        assertTrue(instance.getExpForKill() - oldExpForKill > 0);
    }

    public class AvatarImpl extends Avatar {

        public AvatarImpl(Dimension dim) {
            super(dim);
        }

        @Override
        public void drawAvatar(Graphics2D g2d) {
        }

        @Override
        protected void showAvatar(Graphics2D g2d) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void interact() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void lvlUp() {
            super.lvlUp(1,1,1);
        }
    }
    
}
