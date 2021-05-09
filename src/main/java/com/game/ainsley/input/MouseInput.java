package com.game.ainsley.input;

import com.game.ainsley.handler.InputHandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseInput extends MouseAdapter {

    InputHandler inputHandler = new InputHandler();

    public MouseInput() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    public void mousePressed(MouseEvent event) {
        inputHandler.setRectangle(new Rectangle(event.getX(), event.getY(), 1, 1));
        try {
            inputHandler.checkCollision();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
