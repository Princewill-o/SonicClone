package game;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }




    @Override
    public void keyTyped(KeyEvent e) {
        //not used right now
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            player.startWalking(-7); //move left
            player.removeAllImages();
            player.addImage(new BodyImage("data/SonicRunningL.gif",2.5f));

        } else if (code == KeyEvent.VK_D) {
            player.startWalking(7); //move right
            player.removeAllImages();
            player.addImage(new BodyImage("data/SonicRunningR.gif",2.5f));
        } else if (code == KeyEvent.VK_SPACE) {
            player.jump(17);
            player.removeAllImages();
            player.addImage(new BodyImage("data/SonicDash.gif",2f));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(new BodyImage("data/SonicCharacterRight.gif",3));

        } else if (code == KeyEvent.VK_D) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(new BodyImage("data/SonicCharacterLeft.gif",3));


        }

    }

    public void updatePlayer(Player newPlayer) {
        player = newPlayer;



    }
}