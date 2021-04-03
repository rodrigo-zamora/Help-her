package main;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 *
 */
public class Window extends Canvas {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -6357664197674207224L;

    /**
     * @param width  receives width in pixels
     * @param height receives height in pixels
     * @param title  receives the title of the window
     * @param game   receives the gameStruct
     */
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
