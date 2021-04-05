package com.game.juanito.main;

import com.game.juanito.handler.Handler;
import com.game.juanito.handler.KeyboardInput;
import com.game.juanito.player.Player;
import com.game.juanito.map.Chunk;
import com.game.juanito.enemy.SpawnEnemy;
import com.game.juanito.player.HealthBar;
import com.game.juanito.map.Door;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "El escape de Juanito";
    @Serial
    private static final long serialVersionUID = 2717367914577165013L;
    public static boolean isMovingLeft, isMovingRight;
    public int FPS;
    Player player;
    Chunk chunk = new Chunk();
    HealthBar healthBar = new HealthBar();
    SpawnEnemy spawnEnemy = new SpawnEnemy();
    Handler handler = new Handler();
    Door door = new Door();
    private Thread thread;
    private boolean isRunning = false;

    /**
     *
     */
    public Game() {
        this.addKeyListener(new KeyboardInput(handler));
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player);
        player.setHealth(6);
        handler.addObject(player);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }

    /**
     *
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
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
                //System.out.println("X: " + player.getX() + " Y: " + player.getY());
                //System.out.println("isMovingRight: " + player.isMovingRight() + " isMovingLeft: " + player.isMovingLeft());
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
     *
     */
    private void tick() {

        // Tick all game objects
        handler.tick();

        // Tick healthbar
        healthBar.tick(player.getHealth());

        // Tick chunk
        chunk.tick(player.getX(), player);

        // Tick spawnEnemy
        spawnEnemy.tick(handler, chunk.getX());

        // Tick door
        door.tick(chunk.getIterations(), player.isMovingRight(), player.isMovingLeft());

        isMovingRight = player.isMovingRight();
        isMovingLeft =  player.isMovingLeft();
    }

    /**
     *
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        // Render next chunk
        graphics.drawImage(
                Toolkit.getDefaultToolkit().getImage(chunk.nextChunk()),
                chunk.getX() + 1118,
                -50,
                null
        );

        // Render current chunk
        graphics.drawImage(
                Toolkit.getDefaultToolkit().getImage(chunk.getChunk()),
                chunk.getX(),
                -50,
                null
        );

        // Render previous chunk
        graphics.drawImage(
                Toolkit.getDefaultToolkit().getImage(chunk.previousChunk()),
                chunk.getX() - 1118,
                -50,
                null
        );

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
        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
