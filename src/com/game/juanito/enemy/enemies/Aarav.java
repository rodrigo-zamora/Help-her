package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;

import java.awt.*;
import java.net.URL;

public class Aarav extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    Image aaravLeftImage = toolkit.getImage(aarav);
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.png");
    Image aaravIdleImage = toolkit.getImage(aaravIdle);

    CollisionHandler collisionHandler = new CollisionHandler(210, 150);

    /**
     * Constructor for Aarav class
     *
     * @param x
     * @param y
     * @param id
     */
    public Aarav(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y);
        if (Chunk.getSpeed() != 0)
            x += -Chunk.getSpeed();
        collisionHandler.setRectangle(new Rectangle(x, y, collisionHandler.getWidth(), collisionHandler.getHeight()));
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMoving),
                getX(),
                getY(),
                null
        );
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (collisionHandler.getRectangle() == rectangle) {
            System.out.println("Colisión! ");
        }
    }

    /**
     * Method to get the image of Aarav depending in the player movement
     *
     * @param isMoving receives a boolean
     * @return an Image
     */
    private Image getImage(boolean isMoving) {
        if (isMoving) {
            return aaravLeftImage;
        } else return aaravIdleImage;
    }


}
