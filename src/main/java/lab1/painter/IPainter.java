package lab1.painter;

import lab1.common.Point;

import java.awt.*;

public interface IPainter  {
    void fill(Color color);

    void stroke(Color color, float wide);

    void draw(Shape shape);

    Shape createLine(Point from, Point to);

    Shape createRectangle(Point position, int width, int height);

    Shape createEllipse(Point center, double radius);
}
