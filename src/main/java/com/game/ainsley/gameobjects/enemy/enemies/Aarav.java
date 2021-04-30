package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.gameobjects.ID;

import java.awt.*;
import java.net.URL;

public class Aarav extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    Image aaravLeftImage = toolkit.getImage(aarav);
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.png");
    Image aaravIdleImage = toolkit.getImage(aaravIdle);

    CollisionHandler collisionHandler = new CollisionHandler(210, 70);

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

    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y + 79);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMoving()),
                getX(),
                getY(),
                null
        );
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

}
