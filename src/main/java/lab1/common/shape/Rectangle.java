package lab1.common.shape;

import lab1.common.IShape;
import lab1.painter.IPainter;
import lab1.common.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements IShape {
    private Point leftTop;
    private Point rightBottom;
    private int height;
    private int width;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.height = rightBottom.getY() - leftTop.getY();
        this.width = rightBottom.getX() - leftTop.getX();
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    @Override
    public String getName() {
        return "RECTANGLE";
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
    public Shape getShape() {
        return new Rectangle2D.Double(leftTop.getX(), leftTop.getY(), width, height);
    }

    @Override
    public void draw(IPainter painter) {
        painter.draw(getShape());
    }
}
