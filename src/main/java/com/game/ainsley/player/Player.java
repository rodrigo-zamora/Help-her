package com.game.ainsley.player;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.player.inventory.Inventory;
import com.game.ainsley.screen.Screen;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Player extends GameObject {

    private static final CollisionHandler collisionHandler = new CollisionHandler(90, 32);
    private static final Inventory inventory = new Inventory();

    static Image playerImage;
    static Image playerIdle = FileManager.loadImage("player/player.gif");
    static Image playerDamage = FileManager.loadImage("player/playerDamaged.gif");

    static Sound death;
    static Sound damageEffect;

    private static int health = 6;
    private static int speedY;
    private static boolean shouldRender, isDamaged;
    private static int yS;

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
        playerImage = playerIdle;

        try {
            damageEffect = new Sound("sounds/effects/correct.wav");
            death = new Sound("sounds/dead/deadSong.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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
            damageEffect.playSound(0.5F, true);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        if (Player.health == 0) {
            Game.reset();
            Game.setScreen(Screen.DEATH);
            try {
                death.playSound(1, true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
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

    public static boolean getDamageAnimation() {
        return Player.isDamaged;
    }

    public static void damageAnimation(boolean isDamaged) {
        Player.isDamaged = isDamaged;
        if (isDamaged) {
            playerImage = playerDamage;
        } else {
            playerImage = playerIdle;
        }
    }

    public static int getyS() {
        return yS;
    }

    public static void setyS(int yS) {
        Player.yS = yS;
    }

    public static Sound getDamageEffect() {
        return damageEffect;
    }

    /**
     * Tick method
     */
    @Override
    public boolean tick() {
        y += speedY;
        Player.yS = y;
        if (y <= 260)
            y += +5;
        else if (y >= 430)
            y -= 5;

        collisionHandler.setY(y + 92);
        collisionHandler.updateRectangle();
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
    }

    @Override
    public void collision() {

    }
}
