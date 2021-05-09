package com.game.ainsley.map;

import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.player.Player;
import com.game.ainsley.screen.Screen;
import lib.ainsley.FileManager;
import lib.ainsley.Numbers;

import java.awt.*;

public class Door {

    private static final Image doorImage = FileManager.loadImage("map/door.png");

    private static CollisionHandler collisionHandler = new CollisionHandler(80, 160);

    private static boolean shouldRender;
    private static int x = 1500, y;

    private static int randomInteger;

    /**
     *
     */
    public Door() {
        shouldRender = false;
        collisionHandler.setX(x);
        collisionHandler.setY(220);
        setRandomInteger();
    }

    private static void setRandomInteger() {
        randomInteger = Numbers.randomNumberBetween(3, 10);
    }

    /**
     * @return
     */
    public static int getX() {
        return x;
    }

    /**
     * @param x
     */
    public static void setX(int x) {
        Door.x = x;
    }

    /**
     * @return
     */
    public static int getY() {
        return y;
    }

    /**
     * @param y
     */
    public static void setY(int y) {
        Door.y = y;
    }

    /**
     * @return
     */
    public static boolean shouldRender() {
        return shouldRender;
    }

    /**
     * @param shouldRender
     */
    public static void setShouldRender(boolean shouldRender) {
        Door.shouldRender = shouldRender;
    }

    /**
     * @return
     */
    public static CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    /**
     * @param collisionHandler
     */
    public static void setCollisionHandler(CollisionHandler collisionHandler) {
        Door.collisionHandler = collisionHandler;
    }

    /**
     *
     */
    public static void tick() {
        if (Chunk.getIterations() == randomInteger) {
            shouldRender = true;
        }
        if (shouldRender) {
            if (Chunk.getSpeed() > 0) {
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
    public static void render(Graphics graphics) {
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

            if (Player.shouldRender()) {
                graphics.drawString(
                        "Presiona 'E' para entrar",
                        x - 50,
                        200
                );
            } else {
                graphics.drawString(
                        "Presiona 'E' para salir",
                        x - 50,
                        200
                );
            }
        }
    }

    /**
     * Check for collision between player and door
     *
     * @param rectangle receives the player rectangle
     */
    public static void collision() {
        if (Player.getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

    /**
     * Collision between player and door
     */
    private static void collisionIntersect() {

        // Only get new note if player is visible (outside of the door)
        if (Player.shouldRender()) {

            // Make player stop moving
            Player.setSpeedY(0);
            Chunk.setSpeed(0);

            // Set beenFound from latest note to true
            Player.getInventory().getNote(Player.getInventory().getNotesCollected()).setBeenFound(true);
            Player.getInventory().getNote(Player.getInventory().getNotesCollected()).setOpen(false);

            // Increase notes found by 1
            Player.getInventory().setNotesCollected(Player.getInventory().getNotesCollected() + 1);

            // All notes collected
            if (Player.getInventory().getNotesCollected() == 9) {
                Game.setScreen(Screen.WIN);
                Game.reset();
            }

        } else {
            // Change door visibility
            x = -300;

            Chunk.setIterations(0);
            Door.shouldRender = false;
        }

        // Change player visibility
        Player.setShouldRender();
    }
}
