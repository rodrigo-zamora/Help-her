package main;

import java.awt.*;

/**
 *
 */
public class Player extends GameObject{

    Image rightPlayerImage = Toolkit.getDefaultToolkit().getImage("res/player/rightPlayerImage.gif");
    Image leftPlayerImage = Toolkit.getDefaultToolkit().getImage("res/player/leftPlayerImage.gif");

    private int health;

    /**
     *
     * @param x
     * @param y
     * @param id
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     *
     */
    @Override
    public void tick() {
        x += speedX;
        y += speedY;
    }

    /**
     *
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        if(speedX >= 0){
            graphics.drawImage(rightPlayerImage, getX(), getY(), null);
        } else {
            graphics.drawImage(leftPlayerImage, getX(), getY(), null);
        }
    }
}
