package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private Image background;
    private Player player;
    private JLabel coinsLabel;
    private JLabel livesLabel;

    private GameLevel getCoinCount;

    private Player LivesCount;

    private GameLevel level;

    public GameView(GameLevel world, int width, int height) {
        super(world, width, height);
        player = world.getPlayer();


        // Initialize and position the JLabels for coins and lives
        coinsLabel = new JLabel("Coins: " + player.getCoinCount());
        livesLabel = new JLabel("Lives: " + player.getLives());
        coinsLabel.setForeground(Color.BLACK);
        livesLabel.setForeground(Color.BLACK);
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        livesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinsLabel.setBounds(10, 10, 100, 20);
        livesLabel.setBounds(10, 30, 100, 20);
        add(coinsLabel);
        add(livesLabel);
    }

    // Update the display of the coin count
    public void updateCoinCount(int coinCount) {
        coinsLabel.setText("Coins: " + coinCount);
    }

    // Update the display of the player lives
    public void updateLivesCount(int lives) {
        livesLabel.setText("Lives: " + lives);
    }

    // Set the background image based on the current level
    public void setBackgroundForLevel(int level) {
        switch (level) {
            case 1:
            background = new ImageIcon("data/greenhill.png").getImage();
         break;
            case 2:
            background = new ImageIcon("data/marblezone.jpeg").getImage();
            break;

            case 3:
            // Default background
            background = new ImageIcon("data/Space.png").getImage();
            break;
        }
        repaint();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }
}
