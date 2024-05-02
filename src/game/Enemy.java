package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Enemy extends Walker implements StepListener {

    private static final Shape enemyShape = new BoxShape(0.5f, 2f);

    private static final float IMAGE_HEIGHT_SCALE = 0.5f;
    private static final BodyImage enemyImage = new BodyImage("data/enemies.png", 2f);

    private static final float SPEED = 4f;

    private float left, right;
    private final int RANGE = 7;

    private int direction = 1; // Initial direction: right

    public Enemy(GameLevel world) {
        super(world, enemyShape);
        addImage(enemyImage);
        world.addStepListener(this);
        startWalking(SPEED);
    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
        left = position.x - RANGE;
        right = position.x + RANGE;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().x > right){
            startWalking(-SPEED);



        }
        if (getPosition().x < left){
            startWalking(SPEED);

        }
    }

    @Override
    public void postStep(StepEvent e) {
        // No action needed here
    }
}
