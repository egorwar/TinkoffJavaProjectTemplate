package edu.hw2.task2;

public class Rectangle {
    private final int width;
    private final int height;

    private Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}
