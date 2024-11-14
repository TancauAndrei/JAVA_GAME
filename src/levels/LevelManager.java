package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private BufferedImage[] levelSprite;
    private final Level level;

    public LevelManager() {
        importOutsideSprites();
        level = new Level(LoadSave.GetLevelData());


    }

    public Level getCurrentLevel() {
        return level;
    }

    private void importOutsideSprites() {
        BufferedImage temp= LoadSave.getSpriteAtlas(LoadSave.LEVEL_ONE_ATLAS);
        levelSprite=new BufferedImage[48];
        for(int i=0; i<4; i++) {
            for(int j=0; j<12; j++) {
                int index=i*12+j;
                levelSprite[index]=temp.getSubimage(j*32, i*32,32,32);
            }
        }

    }

    public void draw(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT;i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH;j++) {
                int index=level.getSpriteIndex(j,i);
                g.drawImage(levelSprite[index],j*Game.TILES_SIZE,i*Game.TILES_SIZE, Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
        }

    }

    public void update() {

    }
}
