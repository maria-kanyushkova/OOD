package lab1.common.shape;

import lab1.common.Point;
import lab1.common.Shape;
import lab1.canvas.ICanvas;

import java.awt.*;

public class Line extends Shape {
    private Point startPoint;
    private Point endPoint;
    private Color outlineColor;

    public Line(Point startPoint, Point endPoint, Color outlineColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.outlineColor = outlineColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public int getArea() {
        return 0;
    }

    @Override
    public int getPerimeter() {
        return (int) Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2) + Math.pow(endPoint.getY() - startPoint.getY(), 2));
    }

    @Override
    public Color getOutlineColor() {
        return outlineColor;
    }

    @Override
    public String toString() {
        return "Линия:\n" +
                super.toString() +
                "Точка начала отрезка: " + startPoint.toString() + "\n" +
                "Точка конца отрезка: " + endPoint.toString() + "\n";
    }

    @Override
    public void draw(ICanvas canvas) {
        canvas.drawLine(startPoint, endPoint, outlineColor);
    }
}
