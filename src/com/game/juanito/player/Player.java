package com.game.juanito.player;

import com.game.juanito.handler.CollisionHandler;
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
    private static int speedY;
    public CollisionHandler collisionHandler = new CollisionHandler(128, 128);

    /**
     * Constructor for Player class
     *
     * @param x  receives an integer with the Player's X position
     * @param y  receives an integer with the Player's Y position
     * @param id receives an ID with the Player's ID
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public static int getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(int speedY) {
        Player.speedY = speedY;
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
     * Tick method
     */
    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y);
        if (y <= 260)
            y += +5;
        else if (y >= 430)
            y -= 5;
        y += speedY;
        collisionHandler.setRectangle(new Rectangle(75, collisionHandler.getY(), collisionHandler.getWidth(), collisionHandler.getHeight()));
        //System.out.println("Player: " + collisionHandler.getRectangle());
        return true;
    }

    /**
     * Render method for player
     *
     * @param graphics receives the graphic object
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                playerImage,
                75,
                y,
                null);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(
                75,
                collisionHandler.getY(),
                collisionHandler.getWidth(),
                collisionHandler.getHeight());
    }

    @Override
    public void collision(Rectangle rectangle) {

    }
}
