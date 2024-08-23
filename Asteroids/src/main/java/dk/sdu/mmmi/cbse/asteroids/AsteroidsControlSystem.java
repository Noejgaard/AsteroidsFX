package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class AsteroidsControlSystem implements IEntityProcessingService {
    AsteroidsPlugin asteroidsPlugin = new AsteroidsPlugin();
    @Override
    public void process(GameData gameData, World world) {
        //Remove asteroids that are hit
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            if (asteroid.isHit()) {
                splitAsteroid(asteroid, world);
            }
        }

        //Move the asteroids
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            moveAsteroid(asteroid, gameData);
        }

        //Spawn new asteroids if there are less than 3
        if (world.getEntities(Asteroids.class).size() < 3) {
            Entity asteroid = new Asteroids();
            asteroid = asteroidsPlugin.createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private void splitAsteroid(Entity asteroid, World world) {
        world.removeEntity(asteroid);
        int minSize = 5; // Minimum size to split

        if (asteroid.getWidth() > minSize) {
            // Create two smaller asteroids
            Entity asteroid1 = new Asteroids();
            Entity asteroid2 = new Asteroids();

            // Set positions and rotations for the new asteroids
            double size = asteroid.getWidth() / 2;
            asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            asteroid1.setRotation(asteroid.getRotation() + 45); // Adjust rotation as needed

            // Calculates how much the asteroid should move horizontally based on its current rotation and size.
            asteroid1.setX(asteroid.getX() + Math.cos(Math.toRadians(asteroid1.getRotation())) * asteroid1.getWidth() + 10);
            asteroid1.setY(asteroid.getY() + Math.sin(Math.toRadians(asteroid1.getRotation())) * asteroid1.getHeight() + 10);
            asteroid1.setWidth(size );
            asteroid1.setHeight(size);

            asteroid2.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            asteroid2.setRotation(asteroid.getRotation() - 45);
            asteroid2.setX(asteroid.getX() + Math.cos(Math.toRadians(asteroid2.getRotation())) * asteroid2.getWidth() - 10);
            asteroid2.setY(asteroid.getY() + Math.sin(Math.toRadians(asteroid2.getRotation())) * asteroid2.getHeight() - 10);

            asteroid2.setWidth(size );
            asteroid2.setHeight(size );

            // Add new asteroids to the world
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }

    private void moveAsteroid(Entity asteroid, GameData gameData) {
        double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
        double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

        asteroid.setX(asteroid.getX() - changeX * 0.5);
        asteroid.setY(asteroid.getY() - changeY * 0.5);

        int outOfBounds = 5;

        if (asteroid.getX() < -outOfBounds) {
            asteroid.setX(gameData.getDisplayWidth() + 1);
        }

        if (asteroid.getX() > gameData.getDisplayWidth() + outOfBounds) {
            asteroid.setX(-1);
        }

        if (asteroid.getY() < -outOfBounds) {
            asteroid.setY(gameData.getDisplayHeight() + 1);
        }

        if (asteroid.getY() > gameData.getDisplayHeight() + outOfBounds) {
            asteroid.setY(-1);
        }
    }
}
