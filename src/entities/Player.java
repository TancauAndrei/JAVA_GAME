package entities;

import main.Game;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;


public class Player extends Entity {

    private BufferedImage[][] animation;
    private int animationTick,animationIndex;
    private int playerAction = IDLE;
    private boolean moving = false;

    private int[][] levelData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    private float playerSpeed = 2.0f;

    private boolean left,up,right,down;
    private boolean attacking;

    private float airSpeed = 0f;
    private final float gravity = 0.04f*Game.SCALE;
    private final float jumpSpeed = -2.25f*Game.SCALE;
    private final float fallSpeedAfterCollision = 0.5f*Game.SCALE;
    private boolean inAir = false;
    private boolean jump = false;



    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20 * Game.SCALE, 28*Game.SCALE);
    }


    public void update(){
        updatePosition();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animation[playerAction][animationIndex],
                (int)(hitbox.x-xDrawOffset),
                (int)(hitbox.y-yDrawOffset),
                width,
                height,
                null);
        drawHitbox(g);
    }

    private void loadAnimations() {

        BufferedImage img= LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animation=new BufferedImage[9][6];
            for (int i = 0; i < animation.length; i++) {
                for (int j = 0; j < animation[i].length; j++) {
                    animation[i][j] = img.getSubimage(j*64,i*40,64,40);
                }
            }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
        if(!IsEntityOnFloor(hitbox,levelData)){
            inAir = true;
        }
    }

    private void setAnimation() {

        int startAnimation=playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if(inAir){
            if(airSpeed < 0)
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        if(attacking){
            playerAction = ATTACK_1;
        }
        if( startAnimation != playerAction){
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updateAnimation(){
        animationTick ++;
        int animationSpeed = 15;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex ++;
            if(animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePosition() {

        moving=false;

        if(jump){
            jump();
        }

        if(!left&& !right && !inAir)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;

        if(!inAir)
            if(!IsEntityOnFloor(hitbox, levelData))
                inAir = true;



        if(inAir){
            if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }else {
                hitbox.y = GetEntityPosUnderRoofOrAboveFloor(hitbox,airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        }else
            updateXPos(xSpeed);

        moving=true;
    }

    private void jump() {
        if(inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0f;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitbox.x+xSpeed, hitbox.y,hitbox.width,hitbox.height,levelData)){
            hitbox.x += xSpeed;
        }else{
            hitbox.x = GetEntityPosNextToWall(hitbox,xSpeed) ;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void resetDirBoolean() {
        up = false;
        right = false;
        left = false;
        right = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public float getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(float playerSpeed) {
        this.playerSpeed=playerSpeed;
    }

    public void setJump(boolean jump) {
        this.jump=jump;
    }



}
