package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;
import platformgame.game.Game;
import platformgame.graphics.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    Texture tex = Game.getInstance();

    private int type;

    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }

    // movement
    public void tick(LinkedList<GameObject> object) {

    }

    // display
    public void render(Graphics g) {
        // dirt
        if(type == 0) {
            g.drawImage(tex.block[0],(int)x,(int)y,null );
        }
        //grass
        if(type == 1) {
            g.drawImage(tex.block[1],(int)x,(int)y,null );
        }
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
