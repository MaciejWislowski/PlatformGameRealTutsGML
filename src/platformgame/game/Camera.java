package platformgame.game;

import platformgame.framework.GameObject;

import java.util.LinkedList;

public class Camera {

    private float x,y;

    public Camera (float x, float y) {
        this.x = x;
        this.y = y;
    }

    // camera movement
    public void tick(GameObject player) {
        x = -player.getX()+Game.WIDTH/2;
//        y = -player.getY()+Game.HEIGHT*3/4;
    }

    // Getters&Setters
    //Position
    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
