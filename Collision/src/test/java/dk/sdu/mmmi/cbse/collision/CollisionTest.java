package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    private Entity mockAstroid;
    private Entity mockBullet;
    private Collision collision = new Collision();

    @BeforeEach
    void setUp() {
        mockAstroid = new Entity();
        mockAstroid.setX(5);
        mockAstroid.setY(5);
        mockAstroid.setWidth(10);
        mockAstroid.setHeight(10);

        mockBullet = new Entity();
        mockBullet.setX(5);
        mockBullet.setY(5);
        mockBullet.setWidth(5);
        mockBullet.setHeight(5);
    }

    //Test for collision detection
    @Test
    void testCollision() {
        //assertTrue(1 == 1); // Test if the test is working
        assertTrue(collision.detectCollision(mockAstroid, mockBullet)); // Test if the collision is detected
    }

    //Test for no collision detected
    @Test
    void testNoCollision() {
        mockAstroid.setY(50);
        mockAstroid.setX(50);
        assertFalse(collision.detectCollision(mockAstroid, mockBullet));
    }
}