package com.game.juanito.enemy;

import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Enemy extends GameObject {

    URL aaravImage = ClassLoader.getSystemResource("enemies/Aarav.gif");
    URL deidamiaLeftImage = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    //Image deidamiaRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/DeidamiaR.gif");
    URL gereonLeftImage = ClassLoader.getSystemResource("enemies/GereonL.gif");
    //Image gereonRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/GereonR.gif");
    URL nasraLeftImage = ClassLoader.getSystemResource("enemies/NasraL.gif");
    //Image nasraRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/NasraR.gif");
    URL sephtisLeftImage = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    //Image sephtisRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/SephtisR.gif");

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
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(aaravImage), getX(), getY(), null);
        } else if (getID() == ID.Deidamia) {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(deidamiaLeftImage), getX(), getY(), null);
        } else if (getID() == ID.Gereon) {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(gereonLeftImage), getX(), getY(), null);
        } else if (getID() == ID.Nasra) {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(nasraLeftImage), getX(), getY(), null);
        } else if (getID() == ID.Sephtis) {
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage(sephtisLeftImage), getX(), getY(), null);
        } else throw new IllegalStateException("Unexpected value at enemy.render(): " + getID());
    }
}
