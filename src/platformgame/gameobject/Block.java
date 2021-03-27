package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    public Block(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    // movement
    public void tick(LinkedList<GameObject> object) {

    }

    // display
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    // Getters&Setters

    //Position
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    //Velocity
    public float getVelX() { return velX; }
    public float getVelY() { return velY; }
    public void setVelX(float velX) { this.velX = velX; }
    public void setVelY(float velY) { this.velY = velY; }

    // ObjectId
    public ObjectId getId() { return id; }
}
