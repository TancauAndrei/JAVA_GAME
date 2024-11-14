package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y,float width, float height,int[][] levelData) {
        if(!isSolid(x,y,levelData))
            if(!isSolid(x+width,y+height,levelData))
                if(!isSolid(x+width,y,levelData))
                    if(!isSolid(x,y+height,levelData))
                        return true;
        return false;
    }

    private static boolean isSolid(float x, float y,int[][] levelData) {
        if(x<0 || x >= Game.GAME_WIDTH)
            return true;
        if(y<0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if(value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){

        int currentTile = (int)(hitbox.x/Game.TILES_SIZE);

        if(xSpeed>0){
            //right
            int tileXPosition = currentTile*Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPosition + xOffset - 1;

        }else {
            //left
            return currentTile*Game.TILES_SIZE;
        }
    }
    public static float GetEntityPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){

        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);

        if (airSpeed > 0){
            //falling
            int tileYPosition = currentTile*Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPosition + yOffset - 1;
        }else {
            //jumping
            return currentTile*Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData){
        if(!isSolid(hitbox.x, hitbox.y+ hitbox.height + 1, levelData))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y+ hitbox.height + 1, levelData))
                return false;
        return true;

    }



}
