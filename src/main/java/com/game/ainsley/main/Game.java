package com.game.ainsley.main;

import com.game.ainsley.data.LoadManager;
import com.game.ainsley.data.SaveManager;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.player.HealthBar;
import com.game.ainsley.gameobjects.player.Player;
import com.game.ainsley.handler.GameObjectHandler;
import com.game.ainsley.handler.SoundHandler;
import com.game.ainsley.input.KeyboardInput;
import com.game.ainsley.input.MouseInput;
import com.game.ainsley.map.Door;
import com.game.ainsley.map.MapManager;
import com.game.ainsley.screen.Screen;
import com.game.ainsley.screen.Window;
import com.game.ainsley.screen.screens.Credits;
import com.game.ainsley.screen.screens.Death;
import com.game.ainsley.screen.screens.MainMenu;
import com.game.ainsley.screen.screens.Win;
import javafx.embed.swing.JFXPanel;
import lib.ainsley.FileManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "Help her";

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;

    // Game instance
    private static Game game;

    private final MapManager mapManager;
    private final LoadManager loadManager;
    private final SaveManager saveManager;
    private final Door door;
    private final HealthBar healthBar;
    private final GameObjectHandler gameObjectHandler;

    private static final Image pauseImage = FileManager.loadImage("screens/LoadingScreen1.gif");
    private static final Image saveImage = FileManager.loadImage("buttons/saveButtonPixel4.png");
    private static final Image loadImage = FileManager.loadImage("buttons/loadButtonPixel4.png");
    private static final Image menuImage = FileManager.loadImage("buttons/menuButtonPixel4.png");

    // Initialization for fxPanel to avoid java.lang.NullPointerException
    //	at java.base/java.util.Objects.requireNonNull(Objects.java:208)
    static JFXPanel fxPanel;

    private static boolean paused = false;
    private static boolean isMoving;
    private static Screen screen;
    private static int FPS;
    Player player;

    private Thread thread;
    private boolean isRunning = false;

    public Game() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        game = this;

        addMouseListener(new MouseInput());
        addKeyListener(new KeyboardInput());

        new Window(WIDTH, HEIGHT, TITLE, this);
        mapManager = new MapManager();
        loadManager = new LoadManager();
        saveManager = new SaveManager();
        door = new Door();
        gameObjectHandler = new GameObjectHandler();
        healthBar = new HealthBar();

        player = new Player(75, HEIGHT / 2 - 32, ID.Player);
        gameObjectHandler.addObject(player);
        SoundHandler.sceneSound();
    }

    private static void init() {
        fxPanel = new JFXPanel();
        SoundHandler.addSounds();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.setProperty("sun.java2d.opengl", "true");
        screen = Screen.MAIN_MENU;
        init();
        new Game();
    }

    public void reset() {
        gameObjectHandler.getObject().removeIf(tempObject -> tempObject.getID() != ID.Player);

        player.setHealth(6);
        player.setSpeedY(0);
        player.setShouldRender(true);
        player.getInventory().setNotesCollected(0);
        player.getInventory().setReadingNote(10);
        player.getDamageEffect().stopSound();
        player.setPlayerImage(player.getPlayerIdle());

        mapManager.setSpeed(0);
        mapManager.setSpeed(0);
        mapManager.setX(0);
        mapManager.setIterations(0);
        mapManager.setCurrentChunk(1);

        door.setShouldRender(false);
        door.setX(1500);
        for (int i = 0; i < 9; i++) {
            player.getInventory().getNote(i).setBeenFound(false);
            player.getInventory().getNote(i).setOpen(false);
        }
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

            isMoving = mapManager.getSpeed() > 0;

            // Tick all game objects
            gameObjectHandler.tick();

            mapManager.tick();
            door.tick();
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

            case DEATH -> Death.render(graphics);

            case WIN -> Win.render(graphics);

            case GAME -> {

                // Render map
                mapManager.render(graphics);

                // FPS
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                graphics.drawString(
                        "FPS: " + FPS,
                        15,
                        20
                );

                door.render(graphics);

                healthBar.render(graphics);

                // Render all game objects
                gameObjectHandler.render(graphics);

                // Inventory

                graphics.drawImage(
                        player.getInventory().getContainerImage(),
                        252,
                        530,
                        null
                );

                int xOffset = 0;

                for (int i = 0; i < player.getInventory().getNotesCollected(); i++) {
                    graphics.drawImage(
                            player.getInventory().getInventoryIconImage(i),
                            252 + xOffset,
                            530,
                            null
                    );
                    xOffset += 64;
                }

                // Note
                if (player.getInventory().getReadingNote() != 10) {
                    graphics.drawImage(
                            player.getInventory().getInventoryImage(player.getInventory().getReadingNote()),
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

                    graphics.drawImage(
                            saveImage,
                            425,
                            250,
                            null
                    );

                    graphics.drawImage(
                            loadImage,
                            425,
                            355,
                            null
                    );

                    graphics.drawImage(
                            menuImage,
                            425,
                            460,
                            null
                    );
                }
            }
        }

        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * Getters and setters
     */

    public static Game getInstance() {
        return game;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused() {
        Game.paused = !Game.paused;
    }

    public static Boolean getPaused() {
        return Game.paused;
    }

    public static void setPaused(boolean paused) {
        Game.paused = paused;
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
        SoundHandler.sceneSound();
    }

    public Player getPlayer() {
        return player;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public LoadManager getLoadManager() {
        return loadManager;
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public Door getDoor() {
        return door;
    }

    public GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

}
