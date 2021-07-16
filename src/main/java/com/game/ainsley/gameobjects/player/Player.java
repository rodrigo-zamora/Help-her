package com.game.ainsley.gameobjects.player;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.player.inventory.Inventory;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Player extends GameObject {

    private final CollisionHandler collisionHandler = new CollisionHandler(90, 32);
    private final Inventory inventory = new Inventory();

    Image playerImage;
    Image playerIdle = FileManager.loadImage("player/player.gif");
    Image playerDamage = FileManager.loadImage("player/playerDamaged.gif");

    Sound damageEffect;

    private int health = 6;
    private int speedY;
    private boolean shouldRender;
    private int yS;

    // Game instance
    private final Game game;

    /**
     * Constructor for Player class
     *
     * @param x  receives an integer with the Player's X position
     * @param y  receives an integer with the Player's Y position
     * @param id receives an ID with the Player's ID
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
        game = Game.getInstance();
        collisionHandler.setX(x + 20); // 20
        collisionHandler.setY(y); // 0
        shouldRender = true;
        playerImage = playerIdle;
        damageEffect = new Sound("sounds/effects/damage.mp3", Sound.EFFECT);
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
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

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    public Image getPlayerIdle() {
        return playerIdle;
    }

    public void damage() {
        health--;
        if (health == 0) {
            game.reset();
            Game.setScreen(Screen.DEATH);

        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        playerImage = playerIdle;
                        damageEffect.stopSound();
                    }
                },
                900
        );
        damageEffect.playSound(0.5F, true);
        playerImage = playerDamage;
    }

    public boolean shouldRender() {
        return shouldRender;
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    public void setShouldRender() {
        shouldRender = !shouldRender;
    }

    public int getyS() {
        return yS;
    }

    public void setyS(int yS) {
        this.yS = yS;
    }

    public Sound getDamageEffect() {
        return damageEffect;
    }

    /**
     * Tick method
     */
    @Override
    public boolean tick() {
        y += speedY;
        this.yS = y;
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
