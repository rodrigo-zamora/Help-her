package com.game.juanito.player;

import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

/**
 *
 */
public class Player extends GameObject {

    URL rightPlayerImage = ClassLoader.getSystemResource("player/rightPlayerImage.gif");
    URL leftPlayerImage = ClassLoader.getSystemResource("player/leftPlayerImage.gif");

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
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return
     */
    public boolean isMovingRight() {
        return isMovingRight;
    }

    /**
     *
     * @param movingRight
     */
    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    /**
     *
     * @return
     */
    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    /**
     *
     * @param movingLeft
     */
    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    private void changeMoving(String movement){
        if(movement == "left"){
            isMovingRight = false;
            isMovingLeft = true;
        } else if(movement == "right"){
            isMovingRight = true;
            isMovingLeft = false;
        } else if(movement == "none"){
            isMovingLeft = false;
            isMovingRight = false;
        }
    }

    /**
     *
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
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        if (speedX >= 0) {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(rightPlayerImage), 75, getY(), null);
        } else {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(leftPlayerImage), 75, getY(), null);
        }
    }
}
