package com.game.juanito.main;

import com.game.juanito.enemy.SpawnEnemy;
import com.game.juanito.handler.GameObjectHandler;
import com.game.juanito.input.KeyboardInput;
import com.game.juanito.input.MouseInput;
import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.HealthBar;
import com.game.juanito.player.Player;
import com.game.juanito.screen.*;
import com.game.juanito.screen.Window;
import com.game.juanito.screen.screens.Credits;
import com.game.juanito.screen.screens.Death;
import com.game.juanito.screen.screens.Loading;
import com.game.juanito.screen.screens.MainMenu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "El escape de Juanito";

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;

    public static Screen screen;
    public static boolean isMoving;
    public static int FPS;

    Player player;
    Chunk chunk = new Chunk();
    SpawnEnemy spawnEnemy = new SpawnEnemy();
    GameObjectHandler gameObjectHandler = new GameObjectHandler();
    Door door = new Door();

    private Thread thread;
    private boolean isRunning = false;

    /**
     * Constructor for Game class
     */
    public Game() {
        addMouseListener(new MouseInput());
        this.addKeyListener(new KeyboardInput());
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(75, HEIGHT / 2 - 32, ID.Player);
        gameObjectHandler.addObject(player);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        screen = Screen.MAIN_MENU;
        new Game();
    }

    /**
     * Start threat
     */
    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        thread = new Thread(this, "Thread");
        thread.start();
    }

    public synchronized void stop() {
        if (!isRunning) return;
        isRunning = false;
        try {
            System.exit(1);
            thread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Run method
     */
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                FPS = frames;
                frames = 0;
            }

        }
        stop();
    }

    /**
     * Tick method
     */
    private void tick() {

        switch (screen) {

            case GAME -> {
                isMoving = Chunk.getSpeed() > 0;

                // Tick all game objects
                gameObjectHandler.tick(Player.collisionHandler.getRectangle());
                chunk.tick();
                spawnEnemy.tick(gameObjectHandler);
                door.tick();
            }

            case DEATH -> {

            }
        }



    }

    /**
     * Render method
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        switch (screen) {
            case MAIN_MENU -> {
                MainMenu.render(graphics);
            }

            case GAME -> {

                // Render map
                chunk.render(graphics);

                // FPS
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                graphics.drawString(
                        "FPS: " + FPS,
                        15,
                        20
                );

                door.render(graphics);

                HealthBar.render(graphics);

                // Render all game objects
                gameObjectHandler.render(graphics);
            }

            case DEATH -> {
                Death.render(graphics);
            }

            case CREDITS -> {
                Credits.render(graphics);
            }

            case LOADING -> {
                Loading.render(graphics);
            }

        }

        graphics.dispose();
        bufferStrategy.show();
    }
}
