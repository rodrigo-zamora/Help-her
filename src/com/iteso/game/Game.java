package com.iteso.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = -2784846355173580244L;
    
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "Game";

    private Thread thread;
    private boolean isRunning = false;

    private final Handler handler;

    /**
     *
     */
    public Game() {
        handler = new Handler();
        new Window(WIDTH, HEIGHT, TITLE, this);
        handler.addObject(new Player(100, 100, ID.Player));
    }

    /**
     *
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    /**
     *
     */
    public synchronized void stop() {
        try{
            thread.join();
            isRunning = false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(isRunning) {
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    /**
     *
     */
    private void tick() {
        handler.tick();
    }

    /**
     *
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
