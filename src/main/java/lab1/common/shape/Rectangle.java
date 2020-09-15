package lab1.common.shape;

import lab1.Utils;
import lab1.canvas.ICanvas;
import lab1.common.ISolidShape;
import lab1.common.Point;
import lab1.common.Shape;

import java.awt.*;
import java.util.Arrays;

public class Rectangle extends Shape implements ISolidShape {
    private Point leftTop;
    private Point rightBottom;
    private int height;
    private int width;
    private Color outlineColor;
    private Color fillColor;

    public Rectangle(Point leftTop, Point rightBottom, Color outlineColor, Color fillColor) {
        this.height = rightBottom.getY() - leftTop.getY();
        this.width = rightBottom.getX() - leftTop.getX();
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getArea() {
        return width * height;
    }

    @Override
    public int getPerimeter() {
        return 2 * (width + height);
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
        return "Прямоугольник:\n" +
                super.toString() +
                "Цвет заливки: " + Utils.colorToString(fillColor) + "\n" +
                "Левая верхняя точка прямоугольника: " + leftTop.toString() + "\n" +
                "Правая нижняя точка прямоугольника: " + rightBottom.toString() + "\n" +
                "Ширина прямоугольника: " + width + "\n" +
                "Высота прямоугольника: " + height + "\n";
    }

    @Override
    public void draw(ICanvas canvas) {
        Point rightTop = new Point(rightBottom.getX(), leftTop.getY());
        Point leftBottom = new Point(leftTop.getX(), rightBottom.getY());
        canvas.fillPolygon(Arrays.asList(leftTop, rightTop, rightBottom, leftBottom), fillColor);
        canvas.drawLine(leftTop, rightTop, outlineColor);
        canvas.drawLine(rightTop, rightBottom, outlineColor);
        canvas.drawLine(rightBottom, leftBottom, outlineColor);
        canvas.drawLine(leftBottom, leftTop, outlineColor);
    }
}
