package main;

import map.*;
import player.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;
    
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "El escape de Juanito";

    private Thread thread;
    private boolean isRunning = false;

    private final Handler handler;

    public int FPS, UPS;

    Player player;

    Chunk chunk = new Chunk();

    HealthBar healthBar = new HealthBar();

    /**
     *
     */
    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyboardInput(handler));
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player);
        player.setHealth(6);
        handler.addObject(player);

    }

    /**
     *
     */
    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this, "Thread");
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning) return;
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
    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                FPS = frames;
                updates = 0;
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
        healthBar.tick(player.getHealth());
        chunk.tick(player.getX(), player);
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

        // Render previous chunk
        graphics.drawImage(
                chunk.previousChunk(chunk.getCurrentChunk()),
                chunk.getX()-1118,
                -50,
                null
        );

        // Render current chunk
        graphics.drawImage(
                chunk.getChunk(chunk.getCurrentChunk()),
                chunk.getX(),
                -50,
                null
        );

        // Render next chunk
        graphics.drawImage(
                chunk.nextChunk(chunk.getCurrentChunk()),
                chunk.getX()+1118,
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

        // Health
        healthBar.render(graphics, 650, 5);

        // Render all game objects
        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }
}
