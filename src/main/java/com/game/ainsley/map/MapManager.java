package com.game.ainsley.map;

import com.game.ainsley.gameobjects.enemy.SpawnEnemy;
import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;

import java.awt.*;

public class MapManager {

    private final Game game;

    public MapManager() {
        game = Game.getInstance();
    }

    /**
     * Images with our map chunks
     */

    private final Image chunkImage1 = FileManager.loadImage("map/chunks/1.png");
    private final Image chunkImage2 = FileManager.loadImage("map/chunks/2.png");
    private final Image chunkImage3 = FileManager.loadImage("map/chunks/3.png");
    private final Image chunkImage4 = FileManager.loadImage("map/chunks/4.png");
    private final Image chunkImage5 = FileManager.loadImage("map/chunks/5.png");
    private final Image chunkImage6 = FileManager.loadImage("map/chunks/6.png");
    private final Image chunkImage7 = FileManager.loadImage("map/chunks/7.png");
    private final Image chunkImage8 = FileManager.loadImage("map/chunks/8.png");

    /**
     * Variables for our MapManager class
     */

    private int speed;
    private int x = 0;
    private int iterations = 0;
    private int currentChunk = 1;

    /**
     * This method returns the Image to be displayed at the next chunk
     *
     * @return an Image of the next chunk
     */
    private Image nextChunk() {
        return switch (currentChunk) {
            case 1 -> chunkImage2;
            case 2 -> chunkImage3;
            case 3 -> chunkImage4;
            case 4 -> chunkImage5;
            case 5 -> chunkImage6;
            case 6 -> chunkImage7;
            case 7 -> chunkImage8;
            case 8 -> chunkImage1;
            default -> throw new IllegalStateException("Unexpected value at nextChunk: " + currentChunk);
        };
    }

    /**
     * This method returns the Image to be displayed at the current chunk
     *
     * @return an Image of the current chunk
     */
    private Image getChunk() {
        return switch (currentChunk) {
            case 1 -> chunkImage1;
            case 2 -> chunkImage2;
            case 3 -> chunkImage3;
            case 4 -> chunkImage4;
            case 5 -> chunkImage5;
            case 6 -> chunkImage6;
            case 7 -> chunkImage7;
            case 8 -> chunkImage8;
            default -> throw new IllegalStateException("Unexpected value at getChunk: " + currentChunk);
        };
    }

    /**
     * This method adds the next chunk
     */
    private void addChunk() {
        if (currentChunk == 8) {
            currentChunk = 1;
        } else {
            currentChunk++;
        }
    }

    /**
     * This method calculates which chunk should be displayed and where
     *
     * @param gameObjectHandler receives a GameObjectHandler to spawn the enemies
     */
    public void tick() {
        x += -speed;
        if (x < -1118) {

            /*
             * Changes currentChunk to the next possible chunk, and
             * set's chunk's x position to 0, to start the process again
             */
            addChunk();
            setX(0);

            iterations++;

            game.getGameObjectHandler().addObject(SpawnEnemy.spawn());

        }
    }

    /**
     * This method renders the current chunk and next chunk
     *
     * @param graphics receives the graphics object
     */
    public void render(Graphics graphics) {

        // Render current chunk
        graphics.drawImage(
                getChunk(),
                x,
                -50,
                null
        );

        // Render next chunk
        graphics.drawImage(
                nextChunk(),
                x + 1118,
                -50,
                null
        );

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getCurrentChunk() {
        return currentChunk;
    }

    public void setCurrentChunk(int currentChunk) {
        this.currentChunk = currentChunk;
    }
}
