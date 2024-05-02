package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Coin extends DynamicBody {
    private static final Shape coinShape = new CircleShape(0.5f);
    private static final BodyImage coinImage = new BodyImage("data/SonicCoins.gif", 1);

    private GameLevel world;
    public Coin(GameLevel world) {
        super(world,new CircleShape(0.5f));
        this.world = world;

        addImage(coinImage);
        setPosition(new Vec2(0, 20));
        setGravityScale(0.5f);
    }
    public void collect() {
        destroy(); // Remove the coin from the world
        world.incrementCoinCount(); // Increment the coin count in the GameWorld
    }

}
