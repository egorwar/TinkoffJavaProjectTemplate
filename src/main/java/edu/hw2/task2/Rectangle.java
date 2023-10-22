package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle setWidth(int width) {

        Rectangle changedWidth = new Rectangle();
        changedWidth.height = this.height;
        changedWidth.width = width;

        return changedWidth;
    }

    public Rectangle setHeight(int height) {

        Rectangle changedHeight = new Rectangle();
        changedHeight.width = this.width;
        changedHeight.height = height;

        return changedHeight;
    }

    public double area() {
        return width * height;
    }
}
