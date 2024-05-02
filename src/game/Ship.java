package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

    public class Ship extends StaticBody {

    private GameLevel world;

    private static final float IMAGE_HEIGHT_SCALE = 0.5f;

    private static final Shape shipShape = new BoxShape(0.5f, 2f);
    private static final BodyImage shipImage = new BodyImage("data/Ship.png", 2f);

    public Ship(GameLevel world) {
        super(world, shipShape);
        this.world = world;
        addImage(shipImage); // Add the ship image to the body
    }
}
