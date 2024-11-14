package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {


    private final GamePanel gamePanel;
    private Player player;
    private LevelManager levelManager;

    public static final int TILES_DEFAULT_SIZE = 32;
    public final static  float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final  int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final  int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    private final int START_X_POS = 40;
    private final int START_Y_POS = 250;

    public Game (){
        initClasses();

        gamePanel=new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        levelManager=new LevelManager();
        player = new Player(START_X_POS*SCALE,START_Y_POS*SCALE,(int)SCALE*64,(int)SCALE*40);
        player.loadLevelData(levelManager.getCurrentLevel().levelData());

    }

    private void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void update(){
        levelManager.update();
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
        levelManager.draw(g);
    }

    @Override
    public void run() {
        int FPS_SET = 120;
        int UPS_SET = 200;

        double timePerFrame =1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if(System.currentTimeMillis()-lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS_SET: "+frames+ "|UPS: "+updates);
                frames = 0;
                updates = 0;
            }
        }

    }
    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBoolean();
    }
}
