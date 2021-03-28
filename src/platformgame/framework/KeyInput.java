package platformgame.framework;

import platformgame.game.Handler;
import platformgame.gameobject.Bullet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput (Handler handler) {
        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Player) {
                if(key == KeyEvent.VK_D) tempObject.setVelX(5);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_W && !tempObject.isJumping()) {
                    tempObject.setVelY(-10);
                    tempObject.setJumping(true);
                }
                if(key == KeyEvent.VK_SPACE && !tempObject.isShooting())  {
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(),ObjectId.Bullet, tempObject.getFacing()*15, handler));
                    tempObject.setShooting(true);
                }
            }

        }
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

    }

    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Player) {
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
            }
            if(key == KeyEvent.VK_SPACE && tempObject.isShooting())  {
                tempObject.setShooting(false);
            }

        }
    }
}
