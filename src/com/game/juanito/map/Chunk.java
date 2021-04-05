package com.game.juanito.map;

import com.game.juanito.player.Player;

import java.net.URL;

public class Chunk {

    /**
     * Images with our map chunks
     */

    URL chunk1 = ClassLoader.getSystemResource("map/1.png");
    URL chunk2 = ClassLoader.getSystemResource("map/2.png");
    URL chunk3 = ClassLoader.getSystemResource("map/3.png");
    URL chunk4 = ClassLoader.getSystemResource("map/4.png");
    URL chunk5 = ClassLoader.getSystemResource("map/5.png");
    URL chunk6 = ClassLoader.getSystemResource("map/6.png");
    URL chunk7 = ClassLoader.getSystemResource("map/7.png");
    URL chunk8 = ClassLoader.getSystemResource("map/8.png");
    /**
     * Variables for our Chunk class
     */
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
     * Getter for currentChunk
     *
     * @return an integer
     */
    public int getCurrentChunk() {
        return currentChunk;
    }

    /**
     * Setter for currentChunk
     *
     * @param currentChunk receives an integer
     */
    public void setCurrentChunk(int currentChunk) {
        this.currentChunk = currentChunk;
    }

    /**
     * @return
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * @param iterations
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    /**
     * This method returns the Image to be displayed at the next chunk
     *
     * @return an Image of the next chunk
     */
    public URL nextChunk() {
        return switch (currentChunk) {
            case 1 -> chunk2;
            case 2 -> chunk3;
            case 3 -> chunk4;
            case 4 -> chunk5;
            case 5 -> chunk6;
            case 6 -> chunk7;
            case 7 -> chunk8;
            case 8 -> chunk1;
            default -> throw new IllegalStateException("Unexpected value at nextChunk: " + currentChunk);
        };
    }

    /**
     * This method returns the Image to be displayed at the previous chunk
     *
     * @return an Image of the previous chunk
     */
    public URL previousChunk() {
        return switch (currentChunk) {
            case 1 -> chunk8;
            case 2 -> chunk1;
            case 3 -> chunk2;
            case 4 -> chunk3;
            case 5 -> chunk4;
            case 6 -> chunk5;
            case 7 -> chunk6;
            case 8 -> chunk7;
            default -> throw new IllegalStateException("Unexpected value at previousChunk: " + currentChunk);
        };
    }

    /**
     * This method returns the Image to be displayed at the current chunk
     *
     * @return an Image of the current chunk
     */
    public URL getChunk() {
        return switch (currentChunk) {
            case 1 -> chunk1;
            case 2 -> chunk2;
            case 3 -> chunk3;
            case 4 -> chunk4;
            case 5 -> chunk5;
            case 6 -> chunk6;
            case 7 -> chunk7;
            case 8 -> chunk8;
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
     * This method adds the previous chunk
     */
    private void addChunkNegative() {
        if (currentChunk == 1) {
            currentChunk = 8;
        } else {
            currentChunk--;
        }
    }

    /**
     * This method calculates which chunk should be displayed and where
     *
     * @param playerX receives the current position in X of the player
     * @param player  receives the player object
     */
    public void tick(int playerX, Player player) {
        this.setX(-playerX + 508);
        if (x <= -1118) {
            addChunk();
            player.setX(508);
            iterations++;
            System.out.println("Iterations: " + iterations);
        } else if (x >= 1118) {
            addChunkNegative();
            player.setX(508);
        }
    }
}
