package game;


import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel{

    public Level1(Game game) {
        super(game);

        getPlayer().setPosition(new Vec2(-2, -8));
        getEnemy().setPosition(new Vec2(-8, -10));

    }



    @Override
    public boolean isComplete() {
        return getPlayer().getCoinCount() > 0;
    }
}


