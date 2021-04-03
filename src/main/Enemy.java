package main;

import java.awt.*;

public class Enemy extends GameObject {

    Image aaravImage = Toolkit.getDefaultToolkit().getImage("res/enemies/Aarav.gif");
    Image deidamiaLeftImage = Toolkit.getDefaultToolkit().getImage("res/enemies/DeidamiaL.gif");
    Image deidamiaRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/DeidamiaR.gif");
    Image gereonLeftImage = Toolkit.getDefaultToolkit().getImage("res/enemies/GereonL.gif");
    Image gereonRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/GereonR.gif");
    Image nasraLeftImage = Toolkit.getDefaultToolkit().getImage("res/enemies/NasraL.gif");
    Image nasraRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/NasraR.gif");
    Image sephtisLeftImage = Toolkit.getDefaultToolkit().getImage("res/enemies/SephtisL.gif");
    Image sephtisRightImage = Toolkit.getDefaultToolkit().getImage("res/enemies/SephtisR.gif");

    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += speedX;
    }

    @Override
    public void render(Graphics graphics) {
        if (getID() == ID.Aarav) {
            graphics.drawImage(aaravImage, getX(), getY(), null);
        } else if (getID() == ID.Deidamia) {
            graphics.drawImage(deidamiaLeftImage, getX(), getY(), null);
        } else if (getID() == ID.Gereon) {
            graphics.drawImage(gereonLeftImage, getX(), getY(), null);
        } else if (getID() == ID.Nasra) {
            graphics.drawImage(nasraLeftImage, getX(), getY(), null);
        } else if (getID() == ID.Sephtis) {
            graphics.drawImage(sephtisLeftImage, getX(), getY(), null);
        } else throw new IllegalStateException("Unexpected value at enemy.render(): " + getID());
    }
}
