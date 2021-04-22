package com.game.juanito.map;

import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.player.Player;

import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Door {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    private static final URL door = ClassLoader.getSystemResource("map/door.png");
    private static final Image doorImage = toolkit.getImage(door);

    public static CollisionHandler collisionHandler = new CollisionHandler(80, 160);

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
        Random random = new Random();
        randomInteger = random.nextInt(3);
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
     *
     */
    public static void tick() {
        if (Chunk.getIterations() == randomInteger) {
            shouldRender = true;
        }
        if (shouldRender) {
            collisionHandler.setX(x);
            collisionHandler.setY(y + 220);
            collisionHandler.setRectangle(
                    new Rectangle(
                            collisionHandler.getX(),
                            collisionHandler.getY(),
                            collisionHandler.getWidth(),
                            collisionHandler.getHeight()
                    )
            );

            if (Chunk.getSpeed() > 0) {
                x -= 5;
            }
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

            // collision / border
            graphics.setColor(Color.BLUE);
            graphics.drawRect(
                    collisionHandler.getX(),
                    collisionHandler.getY(),
                    collisionHandler.getWidth(),
                    collisionHandler.getHeight()
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
     * @param rectangle receives the player rectangle
     */
    public static void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
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

            // Increase notes found by 1
            Player.getInventory().setNotesCollected(Player.getInventory().getNotesCollected() + 1);

            // Set beenFound from latest note to true
            Player.getInventory().getNote(Player.getInventory().getNotesCollected()).setBeenFound(true);

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
