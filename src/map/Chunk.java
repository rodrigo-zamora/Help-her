package map;

import main.Player;

import java.awt.*;

public class Chunk {

    /**
     *  Variables for our Chunk class
     */
    private int currentChunk;
    private int x;

    /**
     *  Images with our map chunks
     */
    Image chunk1 = Toolkit.getDefaultToolkit().getImage("res/map/1.png");
    Image chunk2 = Toolkit.getDefaultToolkit().getImage("res/map/2.png");
    Image chunk3 = Toolkit.getDefaultToolkit().getImage("res/map/3.png");
    Image chunk4 = Toolkit.getDefaultToolkit().getImage("res/map/4.png");
    Image chunk5 = Toolkit.getDefaultToolkit().getImage("res/map/5.png");
    Image chunk6 = Toolkit.getDefaultToolkit().getImage("res/map/6.png");
    Image chunk7 = Toolkit.getDefaultToolkit().getImage("res/map/7.png");
    Image chunk8 = Toolkit.getDefaultToolkit().getImage("res/map/8.png");

    /**
     *  Constructor for our Chunk class
     */
    public Chunk() {
        currentChunk = 1;
        x = 0;
    }

    /**
     *  This method returns the Image to be displayed at the next chunk
     * @param currentChunk  receives an integer with the current chunk
     * @return              an Image of the next chunk
     */
    public Image nextChunk(int currentChunk) {
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
     *  This method returns the Image to be displayed at the previous chunk
     * @param currentChunk  receives an integer with the current chunk
     * @return              an Image of the previous chunk
     */
    public Image previousChunk(int currentChunk) {
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
     *  This method returns the Image to be displayed at the current chunk
     * @param currentChunk  receives an integer with the current chunk
     * @return              an Image of the current chunk
     */
    public Image getChunk(int currentChunk) {
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
     *  Getter for x
     * @return  an integer
     */
    public int getX() {
        return x;
    }

    /**
     *  Setter for X
     * @param x receives an integer
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for currentChunk
     * @param currentChunk receives an integer
     */
    public void setCurrentChunk(int currentChunk){
        this.currentChunk = currentChunk;
    }

    /**
     * Getter for currentChunk
     * @return an integer
     */
    public int getCurrentChunk(){
        return currentChunk;
    }

    /**
     *  This method adds the next chunk
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
     * @param playerX   receives the current position in X of the player
     * @param player    receives the player object
     */
    public void tick(int playerX, Player player) {
        this.setX(-playerX + 508);
        if (x <= -1118) {
            addChunk();
            player.setX(508);
        } else if (x >= 1118) {
            addChunkNegative();
            player.setX(508);
        }
    }
}
