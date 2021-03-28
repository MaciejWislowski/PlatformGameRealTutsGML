package platformgame.game;

import platformgame.framework.KeyInput;
import platformgame.framework.ObjectId;
import platformgame.gameobject.Block;
import platformgame.gameobject.Gate;
import platformgame.gameobject.Player;
import platformgame.gameobject.Test;
import platformgame.graphics.BufferedImageLoader;
import platformgame.graphics.Texture;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private  Thread thread;


    public static int WIDTH, HEIGHT;


    // Object

    Handler handler;
    Camera cam;
    static Texture tex;
    public static int level = 1;

    private void init() {
        System.out.println(Physics.G);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        cam = new Camera(0,0);
        tex = new Texture();
        handler = new Handler(cam, tex);




        //temp
        handler.loadImageLevel(tex.getLevel());

        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta>=1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " Ticks: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    // basicly manage movement in time
    private void tick() {

        // object movement
        handler.tick();

        // camera movement connection with player
        for (int i = 0; i < handler.object.size();i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }

        }
    }
    private void render() {
        // Buffer strategy
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        // Initializations&Casting
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        // Draw object
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);

        g2d.translate(cam.getX(), cam.getY()); // begin of cam

        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY()); // end of cam
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }



    public static Texture getInstance() {
        return tex;
    }

    public static void main(String[] args) {
        new Window(800,600,"Platform Game", new Game());
    }
}
