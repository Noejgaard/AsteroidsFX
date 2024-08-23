package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.awt.*;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1: world.getEntities()){
            for (Entity entity2: world.getEntities()){
                if(!entity1.getID().equals(entity2.getID())){
                    if(this.detectCollision(entity1,entity2)){
                        entity1.setHit(true);
                        entity2.setHit(true);
                        System.out.println("Collision Detected: On " + entity1.getID() + " and " + entity2.getID());
                    }
                }
            }
        }
    }

    public Rectangle getBounds(Entity entity){
        return new Rectangle((int) entity.getX(), (int) entity.getY(), (int) entity.getWidth(), (int) entity.getHeight());
    }

    //Detects collision between two entities
    public boolean detectCollision(Entity entity1, Entity entity2){
        Rectangle ent1 = this.getBounds(entity1);
        Rectangle ent2 = this.getBounds(entity2);
        return ent1.intersects(ent2);
    }
}
