package platformgame.game;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private  Thread thread;

    private static final long serialVersionUID = 123;

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread();
        thread.start();
    }

    public void run(){

    }

    public static void main(String[] args) {
        new Window(800,600,"Platform Game", new Game());
    }
}
