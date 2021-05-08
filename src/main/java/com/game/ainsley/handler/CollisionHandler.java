package com.game.ainsley.handler;

import java.awt.*;

public class CollisionHandler {

    private int width, height, x, y;

    private Rectangle rectangle;

    public CollisionHandler(int width, int height) {
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void updateRectangle() {
        rectangle.setRect(x, y, width, height);
    }
}
