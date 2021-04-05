package com.game.juanito.enemy;

import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Enemy extends GameObject {

    /**
     * Load images of all enemies
     */

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.gif");
    Image aaravLeftImage = Toolkit.getDefaultToolkit().getImage(aarav);
    Image aaravIdleImage = Toolkit.getDefaultToolkit().getImage(aaravIdle);

    URL deidamiaLeft = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    URL deidamiaIdle = ClassLoader.getSystemResource("enemies/DeidamiaIdle.gif");
    Image deidamiaLeftImage = Toolkit.getDefaultToolkit().getImage(deidamiaLeft);
    Image deidamiaIdleImage = Toolkit.getDefaultToolkit().getImage(deidamiaIdle);

    URL gereonLeft = ClassLoader.getSystemResource("enemies/GereonL.gif");
    URL gereonIdle = ClassLoader.getSystemResource("enemies/GereonIdle.gif");
    Image gereonLeftImage = Toolkit.getDefaultToolkit().getImage(gereonLeft);
    Image gereonIdleImage = Toolkit.getDefaultToolkit().getImage(gereonIdle);

    URL nasraLeft = ClassLoader.getSystemResource("enemies/NasraL.gif");
    URL nasraIdle = ClassLoader.getSystemResource("enemies/NasraIdle.gif");
    Image nasraLeftImage = Toolkit.getDefaultToolkit().getImage(nasraLeft);
    Image nasraIdleImage = Toolkit.getDefaultToolkit().getImage(nasraIdle);

    URL sephtisLeft = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    URL sephtisIdle = ClassLoader.getSystemResource("enemies/SephtisIdle.gif");
    Image sephtisLeftImage = Toolkit.getDefaultToolkit().getImage(sephtisLeft);
    Image sephtisIdleImage = Toolkit.getDefaultToolkit().getImage(sephtisIdle);

    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     * @return a boolean to know if the enemy is being rendered on the player's screen
     */
    @Override
    public boolean tick() {
        if (Game.isMovingRight) {
            x += speedX;
        } else if (Game.isMovingLeft) {
            x -= speedX;
        }
        return x >= -100;
    }

    private Image getImage(ID id, boolean isMovingLeft, boolean isMovingRight) {
        if (id == ID.Aarav) {
            if (isMovingLeft || isMovingRight) {
                return aaravLeftImage;
            } else {
                return aaravIdleImage;
            }
        } else if (getID() == ID.Deidamia) {
            if (isMovingLeft || isMovingRight) {
                return deidamiaLeftImage;
            } else {
                return deidamiaIdleImage;
            }
        } else if (getID() == ID.Gereon) {
            if (isMovingLeft || isMovingRight) {
                return gereonLeftImage;
            } else {
                return gereonIdleImage;
            }
        } else if (getID() == ID.Nasra) {
            if (isMovingLeft || isMovingRight) {
                return nasraLeftImage;
            } else {
                return nasraIdleImage;
            }
        } else if (getID() == ID.Sephtis) {
            if (isMovingLeft || isMovingRight) {
                return sephtisLeftImage;
            } else {
                return sephtisIdleImage;
            }
        } else throw new IllegalStateException("Unexpected value at getImage: " + getID());
    }

    /**
     * This method renders the enemy depending on the player movement
     *
     * @param graphics receives the graphics object
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(getID(), Game.isMovingLeft, Game.isMovingRight),
                getX(),
                getY(),
                null
        );
    }
}
