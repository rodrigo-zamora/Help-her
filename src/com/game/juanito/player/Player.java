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

    URL rightPlayer = ClassLoader.getSystemResource("player/rightPlayerImage.gif");
    Image rightPlayerImage = toolkit.getImage(rightPlayer);
    URL leftImage = ClassLoader.getSystemResource("player/leftPlayerImage.gif");
    Image leftPlayerImage = toolkit.getImage(leftImage);

    private int health;
    private boolean isMovingRight, isMovingLeft;

    /**
     * @param x
     * @param y
     * @param id
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
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
     * Getter for isMovingRight
     *
     * @return a boolean
     */
    public boolean isMovingRight() {
        return isMovingRight;
    }

    /**
     * Setter for isMovingRight
     *
     * @param movingRight receives a boolean
     */
    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    /**
     * Getter for isMovingLeft
     *
     * @return a boolean
     */
    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    /**
     * Setter for isMovingLeft
     *
     * @param movingLeft receives a boolean
     */
    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    /**
     * Method to change player moving flags
     *
     * @param movement receives a string with the current player movement
     */
    private void changeMoving(String movement){
        switch (movement) {
            case "left" -> {
                isMovingRight = false;
                isMovingLeft = true;
            }
            case "right" -> {
                isMovingRight = true;
                isMovingLeft = false;
            }
            case "none" -> {
                isMovingLeft = false;
                isMovingRight = false;
            }
        }
    }

    /**
     * Tick method
     */
    @Override
    public boolean tick() {
        if(speedX > 0){
            changeMoving("right");
        } else if(speedX < 0){
            changeMoving("left");
        } else {
            changeMoving("none");
        }
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
        if (speedX >= 0) {
            graphics.drawImage(rightPlayerImage, 75, getY(), null);
        } else {
            graphics.drawImage(leftPlayerImage, 75, getY(), null);
        }
    }
}
