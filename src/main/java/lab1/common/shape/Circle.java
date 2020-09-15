package lab1.common.shape;

import lab1.Utils;
import lab1.canvas.ICanvas;
import lab1.common.ISolidShape;
import lab1.common.Point;
import lab1.common.Shape;

import java.awt.*;

public class Circle extends Shape implements ISolidShape {
    private final double pi = 3.14;
    private Point center;
    private int radius;
    private Color outlineColor;
    private Color fillColor;

    public Circle(Point center, int radius, Color outlineColor, Color fillColor) {
        this.center = center;
        this.radius = radius;
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public int getArea() {
        return (int) (pi * radius * radius);
    }

    @Override
    public int getPerimeter() {
        return (int) (2 * pi * radius);
    }

    @Override
    public Color getOutlineColor() {
        return outlineColor;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public String toString() {
        return "Круг:\n" +
                super.toString() +
                "Цвет заливки: " + Utils.colorToString(fillColor) + "\n" +
                "Точка центра окружности: " + center.toString() + "\n" +
                "Радиус окружности: " + radius + "\n";
    }

    @Override
    public void draw(ICanvas canvas) {
        canvas.fillCircle(center, radius, fillColor);
        canvas.drawCircle(center, radius, outlineColor);
    }
}
