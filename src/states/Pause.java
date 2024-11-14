package states;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pause extends State implements StateMethods{


    public Pause(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Pause", Game.GAME_WIDTH/2, Game.GAME_HEIGHT/2);
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameStates.state = GameStates.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
