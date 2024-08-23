import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires java.desktop;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.Collision;
    exports dk.sdu.mmmi.cbse.collision;

}