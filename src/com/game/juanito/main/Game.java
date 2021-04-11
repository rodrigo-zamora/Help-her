package com.game.juanito.main;

import com.game.juanito.enemy.SpawnEnemy;
import com.game.juanito.handler.GameObjectHandler;
import com.game.juanito.handler.KeyboardInput;
import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.HealthBar;
import com.game.juanito.player.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "El escape de Juanito";

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;

    public static boolean isMoving;
    public int FPS;

    Player player;
    Chunk chunk = new Chunk();
    HealthBar healthBar = new HealthBar();
    SpawnEnemy spawnEnemy = new SpawnEnemy();
    GameObjectHandler gameObjectHandler = new GameObjectHandler();
    Door door = new Door();

    private Thread thread;
    private boolean isRunning = false;

    /**
     * Constructor for Game class
     */
    public Game() {
        this.addKeyListener(new KeyboardInput());
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player);
        player.setHealth(6);
        gameObjectHandler.addObject(player);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
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

        isMoving = Chunk.getSpeed() > 0;

        // Tick all game objects
        gameObjectHandler.tick(player.collisionHandler.getRectangle());

        // Tick healthbar
        healthBar.tick(player.getHealth());

        // Tick chunk
        chunk.tick();

        // Tick spawnEnemy
        spawnEnemy.tick(gameObjectHandler, chunk.getX());

        // Tick door
        door.tick(chunk.getIterations(), isMoving);



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

        // Door
        door.render(graphics);

        // Health
        healthBar.render(graphics, 650, 5);

        // Render all game objects
        gameObjectHandler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
