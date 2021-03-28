package platformgame.gameobject;

import platformgame.framework.GameObject;
import platformgame.framework.ObjectId;
import platformgame.game.Camera;
import platformgame.game.Game;
import platformgame.game.Handler;
import platformgame.game.Physics;
import platformgame.graphics.Animation;
import platformgame.graphics.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private final float MAX_SPEED = 10;
    private final float gravity = 0.5f;


     private Handler handler;
     private Camera cam;

     Texture tex = Game.getInstance();

     private Animation playerWalkRight, playerWalkLeft;


    public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        this.cam = cam;

        playerWalkRight = new Animation(2, tex.player[1], tex.player[2],tex.player[3], tex.player[4], tex.player[5], tex.player[6], tex.player[7]);
        playerWalkLeft = new Animation(2, tex.player[8], tex.player[9],tex.player[10], tex.player[11], tex.player[12], tex.player[13], tex.player[14]);

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

        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            // Blocks
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
            // Flag
            else if(tempObject.getId() == ObjectId.Gate) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }

            }
        }
    }


    public void render(Graphics g) {
        // Animation walk right
        if(velX > 0 && velY ==0 ) {
            playerWalkRight.drawAnimation(g,(int)x,(int)y);
            facing = 1;
        }
        // Animation walk left
        else if(velX < 0  && velY ==0) {
            playerWalkLeft.drawAnimation(g,(int)x,(int)y);
            facing = -1;
        }
        // Jump in place
        else if(velY !=0 && velX == 0 ) {
            g.drawImage(tex.player[15],(int)x,(int)y,(int)width,(int)height,null );
        }
        // Jump right
        else if(velY !=0 && velX > 0 ) {
            g.drawImage(tex.player[16],(int)x,(int)y,(int)width,(int)height,null );
        }
        // Jump left
        else if(velY !=0 && velX < 0 ) {
            g.drawImage(tex.player[17],(int)x,(int)y,(int)width,(int)height,null );
        }
        else{
            g.drawImage(tex.player[0],(int)x,(int)y,(int)width,(int)height,null );
        }
    }

    // Hitboxes
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
