package lab1.common.shape;

import lab1.common.IShape;
import lab1.painter.IPainter;
import lab1.common.Point;

public class Circle implements IShape {
    private final double pi = 3.14;
    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "CIRCLE";
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
    public void draw(IPainter painter) {
        painter.draw(painter.createEllipse(center, radius));
    }
}
