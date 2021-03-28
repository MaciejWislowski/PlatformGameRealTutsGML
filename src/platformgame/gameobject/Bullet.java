package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;
import platformgame.game.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {

    private Handler handler;

    public Bullet(float x, float y, ObjectId id, int velX, Handler handler) {
        super(x, y, id);
        this.velX = velX;
        this.handler = handler;
    }

    // movement
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        bulletRemover(object);
    }

    // display
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 16,16);
    }

    private void bulletRemover(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);
            if(getBounds().intersects(tempObject.getBounds())) {
                if(tempObject.getId() == ObjectId.Block) {
                    handler.removeObject(this);
                }
            }

        }
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
