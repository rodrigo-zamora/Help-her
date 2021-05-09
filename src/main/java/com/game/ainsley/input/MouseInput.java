package com.game.ainsley.input;

import com.game.ainsley.handler.InputHandler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseInput extends MouseAdapter {

    InputHandler inputHandler = new InputHandler();

    public void mousePressed(MouseEvent event) {
        inputHandler.setRectangle(new Rectangle(event.getX(), event.getY(), 1, 1));
        try {
            inputHandler.checkCollision();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
