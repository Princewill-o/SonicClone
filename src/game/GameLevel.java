package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Timer;
import java.util.TimerTask;

public abstract class GameLevel extends World implements CollisionListener {
    private Player player;
    private Enemy enemy;
    private int coinCount;
    private Game game;

    private Ship ship;

    private boolean gameOverDisplayed;
    private GameView view;

    public GameLevel(Game game) {
        super();
        coinCount = 0;
        player = new Player(this);
        this.game = game;
        gameOverDisplayed = false;

        // Populate it with bodies (e.g., platforms, collectibles, characters)

        // Make a ground platform
        Shape shape = new BoxShape(5, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.addImage(new BodyImage("data/SonicFloor1.png", 4f));
        ground.setPosition(new Vec2(3f, -11.6f));

        // Make a suspended platform
        Shape platformShape = new BoxShape(3, 0.4f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-6, -11.5f));
        platform1.addImage(new BodyImage("data/SonicFloor1.png", 4f));

        //More Suspended Platforms

        Shape shape2 = new BoxShape(-5, 0.5f);
        StaticBody platform = new StaticBody(this, shape);
        platform.setPosition(new Vec2(-5f, -5));
        platform.addImage(new BodyImage("data/SonicFloor1.png", 3f));

        Shape shape3 = new BoxShape(-4, 0.5f);
        StaticBody platform3 = new StaticBody(this, shape);
        platform3.setPosition(new Vec2(8f, 3));
        platform3.addImage(new BodyImage("data/SonicFloor1.png", 3f));

        // Schedule coin dropping
        Timer timer = new Timer();
        timer.schedule(new CoinDropTask(), 0, 5000); // Drop a new coin every 5 seconds

        setGravity(20);

        // Make a character (with an overlaid image)
        player.setPosition(new Vec2(-5, -5));
        player.addCollisionListener(this);

        player.addCollisionListener(new ShipCollision(this, game));

        // Create an instance of Enemy and set its position
        enemy = new Enemy(this);
        enemy.setPosition(new Vec2(-2, -5));
        enemy.addCollisionListener(this);

        // Create an instance of Ship and set its position
        ship = new Ship(this);
        ship.setPosition(new Vec2(7, 7)); // Set the ship's position (adjust as needed)
    }

    // Method to increment the coin count
    public void incrementCoinCount() {
        coinCount++;
        System.out.println("Coin collected! Coin count: " + coinCount);
    }

    // Method to get the current coin count
    public int getCoinCount() {
        return coinCount;
    }

    // Method to get the current player lives
    public int getPlayerLives() {
        return player.getLives();
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Ship getShip() {
        return ship;
    }



    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin && e.getReportingBody() instanceof Player) {
            Coin coin = (Coin) e.getOtherBody();
            coin.collect();
        }

        if (e.getOtherBody() instanceof Enemy && e.getReportingBody() instanceof Player) {
            player.decrementLives();
            if (player.getLives() == 0) {
                game.displayGameOver();
                gameOverDisplayed = true;
            }
        }

        // Check if the player falls off the platform
        if (e.getOtherBody() instanceof StaticBody && e.getReportingBody() instanceof Player) {
            if (e.getReportingBody().getPosition().y < -15) {
                // Respawn the player at the start position
                player.setPosition(new Vec2(4, -5));
            }
        }
    }

    private class CoinDropTask extends TimerTask {
        @Override
        public void run() {
            Coin coin = new Coin(GameLevel.this);
            coin.setPosition(new Vec2((float) Math.random() * 10 - 5, 20));
            coin.addCollisionListener(GameLevel.this);
        }
    }

    public abstract boolean isComplete();
}


