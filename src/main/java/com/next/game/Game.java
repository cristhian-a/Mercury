package com.next.game;

import com.next.graphics.Window;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements Runnable{

    private Thread mainThread;
    private boolean isRunning;
    private Window window;

    public Game() {
        window = new Window();
    }

    public synchronized void start() {
        isRunning = true;
        window.open();

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
        // does all the shit here
        System.out.println("Hello world!");
    }

    private void render() {
        window.repaint();
    }

    @Override
    public void run() {
        while (isRunning) {
            tick();
            render();
        }

        stop();
    }
}
