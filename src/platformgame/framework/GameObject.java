package platformgame.framework;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    // Fields initialization
    protected float x, y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;

    //Constructor
    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // Methods tick&render
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);

    // Getters and setters
    // Position
    public abstract float getX();
    public abstract float getY();
    public abstract void setX(float x);
    public abstract void setY(float y);

    // Velocity
    public abstract float getVelX();
    public abstract float getVelY();
    public abstract void setVelX(float velX);
    public abstract void setVelY(float velY);

    // Id
    public abstract ObjectId getId();
}
