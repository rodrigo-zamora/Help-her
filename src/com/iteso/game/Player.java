package com.iteso.game;

import java.awt.*;

public class Player extends GameObject{

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, 32, 32);
    }
}
