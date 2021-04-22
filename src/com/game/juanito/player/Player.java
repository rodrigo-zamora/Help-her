package com.game.juanito.player;

import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.handler.SoundHandler;
import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;
import com.game.juanito.player.inventory.Inventory;
import com.game.juanito.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 *
 */
public class Player extends GameObject {

    private static CollisionHandler collisionHandler = new CollisionHandler(90, 32);
    private static Inventory inventory = new Inventory();

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static URL player = ClassLoader.getSystemResource("player/player.gif");
    static URL playerDamage = ClassLoader.getSystemResource("player/playerDamaged.gif");
    static Image playerImage = toolkit.getImage(player);
    static URL damageEffect = ClassLoader.getSystemResource("sounds/effects/correct.wav");

    public static int health = 6;
    private static int speedY;
    private static boolean shouldRender;

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
        shouldRender = true;
    }

    public static CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public static Inventory getInventory() {
        return inventory;
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
            Game.screen = Screen.DEATH;
        }
    }

    public static boolean shouldRender() {
        return shouldRender;
    }

    public static void setShouldRender(boolean shouldRender) {
        Player.shouldRender = shouldRender;
    }

    public static void setShouldRender() {
        shouldRender = !shouldRender;
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

        if (shouldRender) {
            graphics.drawImage(
                    playerImage,
                    75,
                    y,
                    null
            );
        }

        // Inventory

        graphics.drawImage(
                inventory.getContainerImage(),
                252,
                530,
                null
        );

        int xOffset = 0;

        for(int i = 0; i < inventory.getNotesCollected(); i++) {
            graphics.drawImage(
                    inventory.getInventoryIconImage(i),
                    252 + xOffset,
                    530,
                    null
            );
            xOffset += 64;
        }

        // Note
        if (inventory.getReadingNote() != 10) {
            graphics.drawImage(
                    inventory.getInventoryImage(inventory.getReadingNote()),
                    204,
                    12,
                    null
            );
        }

    }

    @Override
    public void collision(Rectangle rectangle) {

    }
}
