package com.game.ainsley.map;

import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;
import lib.ainsley.FileManager;
import lib.ainsley.Numbers;
import lib.ainsley.Sound;

import java.awt.*;

public class Door {

    private final Image doorImage = FileManager.loadImage("map/door.png");
    private final Sound close = new Sound("sounds/effects/doorClose.mp3", Sound.EFFECT);
    private final Sound open = new Sound("sounds/effects/doorOpen.mp3", Sound.EFFECT);
    private CollisionHandler collisionHandler = new CollisionHandler(80, 160);
    private boolean shouldRender;
    private int x = 1500, y;

    private int randomInteger;

    private final Game game;

    /**
     *
     */
    public Door() {
        game = Game.getInstance();
        shouldRender = false;
        collisionHandler.setX(x);
        collisionHandler.setY(220);
        setRandomInteger();
    }

    private void setRandomInteger() {
        randomInteger = Numbers.randomNumberBetween(3, 10);
    }

    /**
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return
     */
    public boolean shouldRender() {
        return shouldRender;
    }

    /**
     * @param shouldRender
     */
    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    /**
     * @return
     */
    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    /**
     * @param collisionHandler
     */
    public void setCollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }

    /**
     *
     */
    public void tick() {
        if (game.getMapManager().getIterations() == randomInteger) {
            shouldRender = true;
        }
        if (shouldRender) {
            if (game.getMapManager().getSpeed() > 0) {
                x -= 5;
            }

            collisionHandler.setX(x);
            collisionHandler.setY(y + 220);
            collisionHandler.updateRectangle();

        }
        if (x == -300) {
            x = 1500;
            shouldRender = false;
            setRandomInteger();
        }
    }

    /**
     * @param graphics
     */
    public void render(Graphics graphics) {
        if (shouldRender) {
            graphics.drawImage(
                    doorImage,
                    x,
                    220,
                    null
            );

            // Text
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));

            if (game.getPlayer().shouldRender()) {
                graphics.drawString(
                        "Press 'E' to enter",
                        x - 25,
                        200
                );
            } else {
                graphics.drawString(
                        "Press 'E' to exit",
                        x - 25,
                        200
                );
            }
        }
    }

    /**
     * Check for collision between player and door
     */
    public void collision() {
        if (game.getPlayer().getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

    /**
     * Collision between player and door
     */
    private void collisionIntersect() {

        // Only get new note if player is visible (outside of the door)
        if (game.getPlayer().shouldRender()) {

            open.playSound(1, false);
            close.stopSound();

            // All notes collected
            if (game.getPlayer().getInventory().getNotesCollected() == 9) {
                Game.setScreen(Screen.WIN);
                game.reset();
            }

            // Make player stop moving
            game.getPlayer().setSpeedY(0);
            game.getMapManager().setSpeed(0);

            // Set beenFound from latest note to true
            game.getPlayer().getInventory().getNote(game.getPlayer().getInventory().getNotesCollected()).setBeenFound(true);
            game.getPlayer().getInventory().getNote(game.getPlayer().getInventory().getNotesCollected()).setOpen(false);

            // Increase notes found by 1
            game.getPlayer().getInventory().setNotesCollected(game.getPlayer().getInventory().getNotesCollected() + 1);

        } else {
            // Change door visibility
            x = -300;
            close.playSound(1, false);
            open.stopSound();

            game.getMapManager().setIterations(0);
            this.shouldRender = false;
        }

        // Change player visibility
        game.getPlayer().setShouldRender();
    }
}
