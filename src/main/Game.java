package main;

import entities.Player;
import levels.LevelManager;
import states.GameStates;
import states.*;
import states.Menu;

import java.awt.*;

public class Game implements Runnable {


    public static final float START_X_POS = 40;
    public static final float START_Y_POS = 250;
    private final GamePanel gamePanel;


    public static final int TILES_DEFAULT_SIZE = 32;
    public final static  float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final  int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final  int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private Playing playing;
    private Menu menu;

    public Game (){
        initClasses();

        gamePanel=new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void update(){
        switch (GameStates.state){
            case MENU -> menu.update();
            case PLAYING ->playing.update();
        }
    }

    public void render(Graphics g){
        switch (GameStates.state){
            case MENU -> menu.render(g);
            case PLAYING ->playing.render(g);
        }
    }

    public void windowFocusLost(){
        if (GameStates.state == GameStates.PLAYING)
            playing.windowFocusLost();
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



    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }

}


