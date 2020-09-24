package lab1.common;

import lab1.painter.IPainter;

public interface IShape {
    String getName();

    int getArea();

    int getPerimeter();

    void draw(IPainter painter);
}
