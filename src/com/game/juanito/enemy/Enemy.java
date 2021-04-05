package com.game.juanito.enemy;

import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Enemy extends GameObject {

    URL aaravImage = ClassLoader.getSystemResource("enemies/Aarav.gif");
    URL aaravImageIdle = ClassLoader.getSystemResource("enemies/AaravIdle.gif");

    URL deidamiaLeftImage = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    URL deidamiaIdleImage = ClassLoader.getSystemResource("enemies/DeidamiaIdle.gif");

    URL gereonLeftImage = ClassLoader.getSystemResource("enemies/GereonL.gif");
    URL gereonIdleImage = ClassLoader.getSystemResource("enemies/GereonIdle.gif");

    URL nasraLeftImage = ClassLoader.getSystemResource("enemies/NasraL.gif");
    URL nasraIdleImage = ClassLoader.getSystemResource("enemies/NasraIdle.gif");

    URL sephtisLeftImage = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    URL sephtisIdleImage = ClassLoader.getSystemResource("enemies/SephtisIdle.gif");

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
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(aaravImage), getX(), getY(), null);
            } else {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(aaravImageIdle), getX(), getY(), null);
            }
        } else if (getID() == ID.Deidamia) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(deidamiaLeftImage), getX(), getY(), null);
            } else {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(deidamiaIdleImage), getX(), getY(), null);
            }
        } else if (getID() == ID.Gereon) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(gereonLeftImage), getX(), getY(), null);
            } else {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(gereonIdleImage), getX(), getY(), null);
            }
        } else if (getID() == ID.Nasra) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(nasraLeftImage), getX(), getY(), null);
            } else {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(nasraIdleImage), getX(), getY(), null);
            }
        } else if (getID() == ID.Sephtis) {
            if (Game.isMovingLeft || Game.isMovingRight) {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(sephtisLeftImage), getX(), getY(), null);
            } else {
                graphics.drawImage(Toolkit.getDefaultToolkit().getImage(sephtisIdleImage), getX(), getY(), null);
            }
        } else throw new IllegalStateException("Unexpected value at enemy.render(): " + getID());
    }
}
