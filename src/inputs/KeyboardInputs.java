package inputs;

import main.GamePanel;

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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SHIFT:
                gamePanel.getGame().getPlayer().setPlayerSpeed(gamePanel.getGame().getPlayer().getPlayerSpeed() / 4);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SHIFT:
                gamePanel.getGame().getPlayer().setPlayerSpeed(gamePanel.getGame().getPlayer().getPlayerSpeed()*4);
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getPlayer().setJump(false);
        }
    }
}
