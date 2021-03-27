package platformgame.game;

import platformgame.framework.ObjectId;
import platformgame.gameobject.Test;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private  Thread thread;

    // Object
    Handler handler;

    private void init() {
        handler = new Handler();

        handler.addObject(new Test(5,5, ObjectId.Test));
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

    private void tick() {
        handler.tick();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);

        handler.render(g);
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Window(800,600,"Platform Game", new Game());
    }
}
