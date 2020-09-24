package lab1.common;

import lab1.common.shape.Circle;
import lab1.common.shape.Rectangle;
import lab1.common.shape.Triangle;

public class ShapeFactory {
    public static IShape createTriangle(Point vertex1, Point vertex2, Point vertex3) {
        return new Triangle(vertex1, vertex2, vertex3);
    }

    public static IShape createRectangle(Point leftTop, Point rightBottom) {
        return new Rectangle(leftTop, rightBottom);
    }

    public static IShape createCircle(Point center, int radius) {
        return new Circle(center, radius);
    }
}