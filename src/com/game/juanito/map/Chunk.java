package com.game.juanito.map;

import java.awt.*;
import java.net.URL;

public class Chunk {

    /**
     * Images with our map chunks
     */

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    URL chunk1 = ClassLoader.getSystemResource("map/1.png");
    Image chunkImage1 = toolkit.getImage(chunk1);
    URL chunk2 = ClassLoader.getSystemResource("map/2.png");
    Image chunkImage2 = toolkit.getImage(chunk2);
    URL chunk3 = ClassLoader.getSystemResource("map/3.png");
    Image chunkImage3 = toolkit.getImage(chunk3);
    URL chunk4 = ClassLoader.getSystemResource("map/4.png");
    Image chunkImage4 = toolkit.getImage(chunk4);
    URL chunk5 = ClassLoader.getSystemResource("map/5.png");
    Image chunkImage5 = toolkit.getImage(chunk5);
    URL chunk6 = ClassLoader.getSystemResource("map/6.png");
    Image chunkImage6 = toolkit.getImage(chunk6);
    URL chunk7 = ClassLoader.getSystemResource("map/7.png");
    Image chunkImage7 = toolkit.getImage(chunk7);
    URL chunk8 = ClassLoader.getSystemResource("map/8.png");
    Image chunkImage8 = toolkit.getImage(chunk8);

    /**
     * Variables for our Chunk class
     */

    private static int speed;
    private int currentChunk;
    private int x;
    private int iterations;

    /**
     * Constructor for our Chunk class
     */
    public Chunk() {
        currentChunk = 1;
        x = 0;
        iterations = 0;
    }

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
    public int getX() {
        return x;
    }

    /**
     * Setter for X
     *
     * @param x receives an integer
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for iterations
     *
     * @return an integer
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Setter for iterations
     *
     * @param iterations receives an integer
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

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
     */
    public void tick() {
        x += -speed;
        if (x < -1118) {
            addChunk();
            setX(0);
            iterations++;
        }
    }

    /**
     * This method renders the previous chunk, current chunk and next chunk
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
}
