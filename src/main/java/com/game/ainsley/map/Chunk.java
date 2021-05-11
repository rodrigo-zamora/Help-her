package com.game.ainsley.map;

import com.game.ainsley.gameobjects.enemy.SpawnEnemy;
import com.game.ainsley.handler.GameObjectHandler;
import com.game.ainsley.player.Player;
import lib.ainsley.FileManager;

import java.awt.*;

public class Chunk {

    /**
     * Images with our map chunks
     */

    private static final Image chunkImage1 = FileManager.loadImage("map/chunks/1.png");
    private static final Image chunkImage2 = FileManager.loadImage("map/chunks/2.png");
    private static final Image chunkImage3 = FileManager.loadImage("map/chunks/3.png");
    private static final Image chunkImage4 = FileManager.loadImage("map/chunks/4.png");
    private static final Image chunkImage5 = FileManager.loadImage("map/chunks/5.png");
    private static final Image chunkImage6 = FileManager.loadImage("map/chunks/6.png");
    private static final Image chunkImage7 = FileManager.loadImage("map/chunks/7.png");
    private static final Image chunkImage8 = FileManager.loadImage("map/chunks/8.png");

    /**
     * Variables for our Chunk class
     */

    private static int speed;
    private static int x = 0;
    private static int iterations = 0;
    private static int currentChunk = 1;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Chunk.speed = speed;
    }

    /**
     * Getter for x
     *
     * @return an integer
     */
    public static int getX() {
        return x;
    }

    /**
     * Setter for X
     *
     * @param x receives an integer
     */
    public static void setX(int x) {
        Chunk.x = x;
    }

    /**
     * Getter for iterations
     *
     * @return an integer
     */
    public static int getIterations() {
        return iterations;
    }

    /**
     * Setter for iterations
     *
     * @param iterations receives an integer
     */
    public static void setIterations(int iterations) {
        Chunk.iterations = iterations;
    }

    /**
     * @return
     */
    public static int getCurrentChunk() {
        return currentChunk;
    }

    /**
     * @param currentChunk
     */
    public static void setCurrentChunk(int currentChunk) {
        Chunk.currentChunk = currentChunk;
    }

    /**
     * This method returns the Image to be displayed at the next chunk
     *
     * @return an Image of the next chunk
     */
    private static Image nextChunk() {
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
    private static Image getChunk() {
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
    private static void addChunk() {
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
    public static void tick() {
        x += -speed;
        if (x < -1118) {

            /*
             * Changes currentChunk to the next possible chunk, and
             * set's chunk's x position to 0, to start the process again
             */
            addChunk();
            setX(0);

            iterations++;

            GameObjectHandler.addObject(SpawnEnemy.spawn());

        }
    }

    /**
     * This method renders the current chunk and next chunk
     *
     * @param graphics receives the graphics object
     */
    public static void render(Graphics graphics) {

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
}
