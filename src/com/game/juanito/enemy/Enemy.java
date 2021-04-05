package com.game.juanito.enemy;

import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Enemy extends GameObject {

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

    @Override
    public boolean tick() {
        if(Game.isMovingRight){
            x += speedX;
        } else if(Game.isMovingLeft) {
            x -= speedX;
        }
        return x >= -100;
    }

    @Override
    public void render(Graphics graphics) {
        if (getID() == ID.Aarav) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(aaravLeftImage, getX(), getY(), null);
            } else {
                graphics.drawImage(aaravIdleImage, getX(), getY(), null);
            }
        } else if (getID() == ID.Deidamia) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(deidamiaLeftImage, getX(), getY(), null);
            } else {
                graphics.drawImage(deidamiaIdleImage, getX(), getY(), null);
            }
        } else if (getID() == ID.Gereon) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(gereonLeftImage, getX(), getY(), null);
            } else {
                graphics.drawImage(gereonIdleImage, getX(), getY(), null);
            }
        } else if (getID() == ID.Nasra) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(nasraLeftImage, getX(), getY(), null);
            } else {
                graphics.drawImage(nasraIdleImage, getX(), getY(), null);
            }
        } else if (getID() == ID.Sephtis) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(sephtisLeftImage, getX(), getY(), null);
            } else {
                graphics.drawImage(sephtisIdleImage, getX(), getY(), null);
            }
        } else throw new IllegalStateException("Unexpected value at enemy.render(): " + getID());
    }
}
