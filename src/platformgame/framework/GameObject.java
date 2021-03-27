package platformgame.framework;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    // Fields initialization
    protected float x, y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;

    //Constructor
    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // Methods tick&render
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    // Getters and setters
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

    //Jumping&Falling
    public boolean isFalling() { return falling; }
    public boolean isJumping() { return jumping; }
    public void setFalling(boolean falling) { this.falling = falling; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }

    // ObjectId
    public ObjectId getId() { return id; }
}
