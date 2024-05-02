package game;

import city.cs.engine.*;

public class ShipCollision implements CollisionListener {

    GameLevel level;
    Game g;



    public ShipCollision(GameLevel level, Game game){
        this.level = level;
        g = game;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Ship) {
            System.out.println("Player collided with ship");
            // Additional actions when player collides with ship
            if (!level.isComplete()) {
                g.goToNextLevel();
            }else {
                System.out.println("you need more coins");
            }
        }
    }
}
