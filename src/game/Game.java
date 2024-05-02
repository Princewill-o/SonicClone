package game;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private GameLevel currentlevel;
    private GameView view;
    private JFrame frame;
    private final JLabel gameOverLabel;
    private boolean gameOver;



    PlayerController controller;


    /**
     * Initialise a new Game.
     */
    public Game() {
        // Make an empty game world
        currentlevel = new Level1(this);

        SoundPlayer.playSound("data/Sonic.wav");

        // Make a view to look into the game world
        view = new GameView(currentlevel, 800, 600);

        view.setBackgroundForLevel(1);

        // Game Over Label
        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        gameOverLabel.setForeground(Color.RED); // Set text color to red
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
        gameOverLabel.setBounds(0, 0, 800, 50); // Set the position and size of the label at the top of the screen
        gameOverLabel.setVisible(false); // Initially set to invisible
        view.add(gameOverLabel);



        // Create a PlayerController and link it to the Player object in the world
        controller = new PlayerController(currentlevel.getPlayer());
        // Add the PlayerController as a KeyListener to the view
        view.addKeyListener(controller);

        // Create a Java window (frame) and add the game view to it
        frame = new JFrame("City Game");
        frame.add(view);

        // Enable the frame to quit the application when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // Don't let the frame be resized
        frame.setResizable(false);
        // Size the frame to fit the world view
        frame.pack();
        // Finally, make the frame visible
        frame.setVisible(true);

        // Start our game world simulation!
        currentlevel.start();
        // Request focus to the view so that it can receive keyboard events
        view.requestFocus();
    }


    public void gameOver() {
        // Stop the world
        currentlevel.stop();

        // Remove the game view
        frame.remove(view);

        // Create the game overview
        GameView gameOverView = new GameView(currentlevel, 800, 600);
        frame.add(gameOverView);

        // Repaint the frame
        frame.revalidate();
        frame.repaint();

        // Schedule game termination after 5 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Close the game window and exit the application
                frame.dispose();
                System.exit(0);
            }
        }, 5000);
    }

    public void displayGameOver() {
        gameOverLabel.setVisible(true); // Show the game over label
        gameOver = true;
        currentlevel.stop(); // Stop the game world
        // Set gameOver to true to trigger game termination after 5 seconds
        // Additional actions to perform when game over is displayed
    }

    public void goToNextLevel() {

        System.out.println("Yes, lets go to next level");

        if (currentlevel instanceof Level1) {

            currentlevel.stop();

            Player prevPlayer = currentlevel.getPlayer();

            currentlevel = new Level2(this);

            Player newPlayer = currentlevel.getPlayer();

            view.setBackgroundForLevel(2);


            //level now refer tao the new level
            view.setWorld(currentlevel);

            controller.updatePlayer(currentlevel.getPlayer());

            currentlevel.start();

        } else if (currentlevel instanceof Level2) {
            currentlevel.stop();

            Player prevPlayer = currentlevel.getPlayer();

            currentlevel = new Level2(this);

            Player newPlayer = currentlevel.getPlayer();

            view.setBackgroundForLevel(3);

            //level now refer tao the new level
            view.setWorld(currentlevel);

            controller.updatePlayer(currentlevel.getPlayer());

            currentlevel.start();

        } else if (currentlevel instanceof Level3){



            System.out.println("Well done! Game complete.");

            System.exit(0);
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
