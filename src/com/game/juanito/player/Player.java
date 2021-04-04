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

    /**
     * @param x
     * @param y
     * @param id
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     */
    @Override
    public boolean tick() {
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