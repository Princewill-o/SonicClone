package game;

import city.cs.engine.*;

public class CoinCollisionListener implements CollisionListener {

    private Player player;

    public CoinCollisionListener(Player s){
        this.player = s;
    }
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {
            player.addCoin();
            e.getOtherBody().destroy();
        }
    }
}
