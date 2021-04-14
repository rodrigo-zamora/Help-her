package main;

import java.awt.*;

public class Enemy extends GameObject{

    Image enemyImage = Toolkit.getDefaultToolkit().getImage("res/enemy/enemyImage.gif");

    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(enemyImage, getX(), getY(), null);
    }
}
