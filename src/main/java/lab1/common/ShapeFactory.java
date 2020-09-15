package lab1.common;

import lab1.common.shape.Circle;
import lab1.common.shape.Line;
import lab1.common.shape.Rectangle;
import lab1.common.shape.Triangle;

import java.awt.*;

public class ShapeFactory {
    public static IShape createLine(Point start, Point end, Color outlineColor) {
        return new Line(start, end, outlineColor);
    }

    public static IShape createTriangle(Point vertex1, Point vertex2, Point vertex3, Color outlineColor, Color fillColor) {
        return new Triangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
    }

    public static IShape createRectangle(Point leftTop, Point rightBottom, Color outlineColor, Color fillColor) {
        return new Rectangle(leftTop, rightBottom, outlineColor, fillColor);
    }

    public static IShape createCircle(Point center, int radius, Color outlineColor, Color fillColor) {
        return new Circle(center, radius, outlineColor, fillColor);
    }
}