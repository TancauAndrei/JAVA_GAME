package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    private static float xStart;
    private static float yStart;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        xStart = x;
        yStart = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitbox(Graphics g) {
        //for debugging only
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

}
