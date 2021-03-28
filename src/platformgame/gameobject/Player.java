package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;
import platformgame.game.Game;
import platformgame.game.Handler;
import platformgame.game.Physics;
import platformgame.graphics.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private final float MAX_SPEED = 10;
    private final float gravity = 0.5f;

     private Handler handler;

     Texture tex = Game.getInstance();


    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(falling || jumping) {
            velY += gravity;

            if(velY > Physics.TV) {
                velY = (float) Physics.TV;
            }
        }

        Collision(object);
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);


            if(tempObject.getId() == ObjectId.Block) {
                // Bottom collisions
                if(getBounds().intersects(tempObject.getBounds())) {
                    if(velY >= 20){
                        System.out.println("TOT");
                    }
                    y = tempObject.getY()-height+1;
                    velY = 0;
                    falling = false;
                    jumping = false;

                } else {
                    falling = true;
                }

                // Top collisions
                if(getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY()+32;
                    velY = 0;
                }
                // Right collisions
                if(getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX()-width;
                }
                // Left collisions
                if(getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX()+35;
                }
            }
        }
    }


    public void render(Graphics g) {
        g.drawImage(tex.player[0],(int)x,(int)y,(int)width,(int)height,null );
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
