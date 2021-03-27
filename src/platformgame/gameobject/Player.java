package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private float gravity = 0.05f;
    private final float MAX_SPEED = 10;


    public Player(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
//        y += velY;

        if(falling || jumping) {
            velY += gravity;

            if(velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x,(int)y, (int)width, (int)height);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());

    }

    // Collisions
    // Collision from down
    public Rectangle getBounds() {
        return new Rectangle((int)(x+(width/2)-(width/4)),(int)(y + (height/2)), (int)width/2, (int)height/2 );
    }
    // Collision from top
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x+(width/2)-(width/4)),(int)y, (int)width/2, (int)height / 2 );
    }
    // Collision from right
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+(width*3/4)),(int)y+5, (int)width/4, (int)height - 10 );
    }
    // Collision from left
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x,(int)y+5, (int)width/4, (int)height - 10 );
    }

}
