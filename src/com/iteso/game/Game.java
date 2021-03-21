package com.iteso.game;

import java.awt.*;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = -2784846355173580244L;
    
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Game";

    private Thread thread;
    private boolean isRunning = false;

    public Game()
    {
        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop()
    {
        try{
            thread.join();
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run()
    {

    }

    public static void main(String[] args)
    {
        new Game();
    }
}
