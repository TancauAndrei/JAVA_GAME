package inputs;

import main.GamePanel;
import states.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameStates.state){
            case GameStates.MENU->gamePanel.getGame().getMenu().keyPressed(e);
            case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
            case PAUSED -> gamePanel.getGame().getPause().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameStates.state){
            case GameStates.MENU->gamePanel.getGame().getMenu().keyReleased(e);
            case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
            case PAUSED -> gamePanel.getGame().getPause().keyReleased(e);
        }


    }
}
