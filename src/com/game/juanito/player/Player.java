package com.game.juanito.player;

import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

/**
 *
 */
public class Player extends GameObject {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL player = ClassLoader.getSystemResource("player/rightPlayerImage.gif");
    Image playerImage = toolkit.getImage(player);

    private int health;
    private boolean isMoving;

    /**
     * Constructor for Player class
     *
     * @param x  receives an integer with the Player's X position
     * @param y  receives an integer with the Player's Y position
     * @param id receives an ID with the Player's ID
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
        setWidth(128);
        setHeight(128);
    }

    /**
     * Getter for health
     *
     * @return an integer
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for health
     *
     * @param health receives an integer
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter for isMoving
     *
     * @return a boolean
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Setter for setMoving
     *
     * @param moving receives a boolean
     */
    public void setMoving(boolean moving) {
        isMoving = moving;
    }



    /**
     * Tick method
     */
    @Override
    public boolean tick() {
        isMoving = speedX > 0;
        x += speedX;
        if (y <= 260)
            y += +5;
        else if (y >= 430)
            y -= 5;
        y += speedY;
        return true;
    }

    /**
     * Render method for player
     *
     * @param graphics receives the graphic object
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(playerImage,
                75,
                 y,
                null);
    }

    @Override
    public void collision() {
        System.out.println("collision! p");
    }
}
