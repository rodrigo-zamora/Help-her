package com.game.ainsley.main;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.handler.GameObjectHandler;
import com.game.ainsley.input.KeyboardInput;
import com.game.ainsley.input.MouseInput;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.map.Door;
import com.game.ainsley.player.HealthBar;
import com.game.ainsley.player.Player;
import com.game.ainsley.screen.Screen;
import com.game.ainsley.screen.Window;
import com.game.ainsley.screen.screens.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.Serial;
import java.net.URL;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "Help her";

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;

    private static boolean paused = false;
    private static boolean isMoving;
    private static Screen screen;
    private static int FPS;

    Player player;

    private Thread thread;
    private boolean isRunning = false;

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL pause = ClassLoader.getSystemResource("Screens/IntroLarge.gif");
    private static final Image pauseImage = toolkit.getImage(pause);

    /**
     * Constructor for Game class
     */
    public Game() {
        addMouseListener(new MouseInput());
        this.addKeyListener(new KeyboardInput());
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(75, HEIGHT / 2 - 32, ID.Player);
        GameObjectHandler.addObject(player);
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.opengl", "true");
        screen = Screen.MAIN_MENU;
        new Game();
    }

    /**
     * Getters and setters
     */

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused() {
        Game.paused = !Game.paused;
    }

    public static Boolean getPaused() {
        return Game.paused;
    }

    public static boolean isMoving() {
        return isMoving;
    }

    public static void setMoving(boolean isMoving) {
        Game.isMoving = isMoving;
    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        Game.screen = screen;
    }

    public static int getFPS() {
        return FPS;
    }

    public static void setFPS(int FPS) {
        Game.FPS = FPS;
    }

    /**
     * Start thread
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

        if (screen == Screen.GAME) {

            isMoving = Chunk.getSpeed() > 0;

            // Tick all game objects
            GameObjectHandler.tick();

            Chunk.tick();
            Door.tick();
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

            case MAIN_MENU -> MainMenu.render(graphics);

            case CREDITS -> Credits.render(graphics);

            case LOADING -> Loading.render(graphics);

            case DEATH -> Death.render(graphics);

            case GAME -> {

                // Render map
                Chunk.render(graphics);

                // FPS
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                graphics.drawString(
                        "FPS: " + FPS,
                        15,
                        20
                );

                Door.render(graphics);

                HealthBar.render(graphics);

                // Render all game objects
                GameObjectHandler.render(graphics);

                // Inventory

                graphics.drawImage(
                        Player.getInventory().getContainerImage(),
                        252,
                        530,
                        null
                );

                int xOffset = 0;

                for (int i = 0; i < Player.getInventory().getNotesCollected(); i++) {
                    graphics.drawImage(
                            Player.getInventory().getInventoryIconImage(i),
                            252 + xOffset,
                            530,
                            null
                    );
                    xOffset += 64;
                }

                // Note
                if (Player.getInventory().getReadingNote() != 10) {
                    graphics.drawImage(
                            Player.getInventory().getInventoryImage(Player.getInventory().getReadingNote()),
                            204,
                            12,
                            null
                    );
                }

                // Pause menu
                if (paused) {
                    graphics.drawImage(
                            pauseImage,
                            0,
                            0,
                            null
                    );
                }
            }
        }

        graphics.dispose();
        bufferStrategy.show();
    }
}
