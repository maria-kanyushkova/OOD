package lab1.common;

import lab1.painter.IPainter;

import java.awt.*;

public interface IShape {
    String getName();

    int getArea();

    int getPerimeter();

    Shape getShape();

    void draw(IPainter painter);
}
