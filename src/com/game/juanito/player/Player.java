package com.game.juanito.player;

import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.handler.SoundHandler;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 *
 */
public class Player extends GameObject {

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static URL player = ClassLoader.getSystemResource("player/player.gif");
    static URL playerDamage = ClassLoader.getSystemResource("player/playerDamaged.gif");
    static Image playerImage = toolkit.getImage(player);

    static URL damageEffect = ClassLoader.getSystemResource("sounds/effects/correct.wav");

    private static int health = 6;
    private static int speedY;
    public static CollisionHandler collisionHandler = new CollisionHandler(90, 32); // 90 & 64

    /**
     * Constructor for Player class
     *
     * @param x  receives an integer with the Player's X position
     * @param y  receives an integer with the Player's Y position
     * @param id receives an ID with the Player's ID
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
        collisionHandler.setX(x + 20); // 20
        collisionHandler.setY(y); // 0
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
    public static int getHealth() {
        return health;
    }

    /**
     * Setter for health
     *
     * @param health receives an integer
     */
    public static void setHealth(int health) {
        Player.health = health;
        try {
            SoundHandler.playSound(damageEffect, 0.5F, false);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        if (Player.health == 0) {
            System.out.println("Juanito died");
        }
    }

    public static void damageAnimation(boolean isDamaged) {
        if (isDamaged) {
            playerImage = toolkit.getImage(playerDamage);
        } else {
            playerImage = toolkit.getImage(player);
        }
    }

    /**
     * Tick method
     */
    @Override
    public boolean tick() {
        y += speedY;
        if (y <= 260)
            y += +5;
        else if (y >= 430)
            y -= 5;

        collisionHandler.setY(y + 92); // 65
        collisionHandler.setRectangle(
                new Rectangle(
                        collisionHandler.getX(),
                        collisionHandler.getY(),
                        collisionHandler.getWidth(),
                        collisionHandler.getHeight()
                )
        );
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
                null
        );
        /*graphics.setColor(Color.BLACK);
        graphics.drawRect(
                collisionHandler.getX(),
                collisionHandler.getY(),
                collisionHandler.getWidth(),
                collisionHandler.getHeight()
        );*/
    }

    @Override
    public void collision(Rectangle rectangle) {

    }
}
