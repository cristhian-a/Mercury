package com.next.game;

import com.next.graphics.GamePanel;
import com.next.graphics.SpriteLoader;
import com.next.graphics.SpriteSheet;
import com.next.graphics.Window;
import com.next.io.KeyHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements Runnable{

    private Thread mainThread;
    private boolean isRunning;
    private Window window;
    private KeyHandler keyHandler;

    private SpriteSheet spriteSheet;
    private SpriteLoader spriteLoader;

    public Game() {
        window = new Window();
        keyHandler = new KeyHandler();
    }

    public synchronized void start() {
        isRunning = true;
        window.open(keyHandler);

        try {
            spriteSheet = new SpriteSheet("/spritesheet.png");
            int tileSize = window.getPanel().ORIGINAL_TILE_SIZE;
            spriteLoader = new SpriteLoader(spriteSheet, 25, tileSize, tileSize, 10, 10);
            spriteLoader.setPlayerSprites(window.getPanel().player);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            mainThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tick() {
        window.getPanel().player.tick();
    }

    private void render() {
        window.repaint();
    }

    @Override
    public void run() {
        //Adjust frame rate(fps)
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // 1 million = 1 sec
        double delta = 0;

        // Debug info *(frame rate)*
        int frames = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                this.tick();
                this.render();
                frames++;   // Debug info *(frame rate)*
                delta--;
            }

            // Debug info *(frame rate)*
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }
        }

        stop();
    }
}
