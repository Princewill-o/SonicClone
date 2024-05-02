package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel {

    public Level3(Game game) {
        super(game);

        // Make a platform
        Shape shape = new BoxShape(3, 0.5f);
        StaticBody platform = new StaticBody(this, shape);
        platform.setPosition(new Vec2(-5f, 4));
        platform.addImage(new BodyImage("data/SonicFloor1.png", 3f));

        Shape shape1 = new BoxShape(3, 3f);
        StaticBody platform2 = new StaticBody(this, shape1);
        platform2.setPosition(new Vec2(-5f, 4));
        platform2.addImage(new BodyImage("data/SonicFloor1.png", 3f));

        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(this);
            coin.setPosition(new Vec2(-7 + i * 3, 10));
        }
    }

    @Override
    public boolean isComplete() {
        return getPlayer().getCoinCount() > 3;
    }
}
