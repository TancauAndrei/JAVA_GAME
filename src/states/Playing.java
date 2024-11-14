package states;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.*;

public class Playing extends State implements StateMethods{


    private LevelManager levelManager;
    private Player player;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager();
        player = new Player(START_X_POS*SCALE,START_Y_POS*SCALE,(int)SCALE*64,(int)SCALE*40);
        player.loadLevelData(levelManager.getCurrentLevel().levelData());

    }



    public void windowFocusLost() {
        player.resetDirBoolean();
    }

    @Override
    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;
            case KeyEvent.VK_SHIFT:
                player.setPlayerSpeed(player.getPlayerSpeed() / 4);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                GameStates.state = GameStates.MENU;
                break;
            case KeyEvent.VK_ESCAPE:
                GameStates.state = GameStates.PAUSED;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(false);
                break;
            case KeyEvent.VK_SHIFT:
                player.setPlayerSpeed(player.getPlayerSpeed()*4);
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                player.setJump(false);
        }
    }
}

