package game;
import city.cs.engine.Walker;


import city.cs.engine.*;

public class Player extends Walker {


    private static final Shape PlayerShape = new BoxShape(1, 2.6f);

    private int lives;

    private int coinCount;

    private int credits;

    private StaticBody platform;

    private static final BodyImage image = new BodyImage("data/SonicCharacter.png", 3);

    public void addCoin() {
        coinCount++;
        System.out.println("Score: " +
                "coinCount = " + coinCount);
    }

    public void setPlatform(StaticBody platform) {
        this.platform = platform;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public Player(GameLevel world) {
        super(world, PlayerShape);
        this.addImage(image);

        lives = 3;

        coinCount = 0;
    }

    // Method to decrement lives
    public void decrementLives() {
        lives--;
        System.out.println("Player hit by enemy! Lives remaining: " + lives);


    }

    // Method to get the current lives
    public int getLives() {
        return lives;
    }
}


