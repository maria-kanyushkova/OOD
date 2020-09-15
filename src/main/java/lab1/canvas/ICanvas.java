package lab1.canvas;

import lab1.common.Point;

import java.awt.*;
import java.util.List;

public interface ICanvas {
    void drawLine(Point from, Point to, Color outlineColor);

    void fillPolygon(List<Point> points, Color fillColor);

    void drawCircle(Point center, double radius, Color outlineColor);

    void fillCircle(Point center, double radius, Color fillColor);
}
