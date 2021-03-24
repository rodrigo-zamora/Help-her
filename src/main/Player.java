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
        if(getY() <= 280)
            y = getY() + 5;
        else if(getY() >= 450)
            y = getY() - 5;
        y += speedY;
    }

    /**
     *
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        if(speedX >= 0){
            graphics.drawImage(rightPlayerImage, 75, getY(), null);
        } else {
            graphics.drawImage(leftPlayerImage, 75, getY(), null);
        }
    }
}
