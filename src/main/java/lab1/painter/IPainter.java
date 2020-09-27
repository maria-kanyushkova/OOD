package lab1.painter;

import java.awt.*;

public interface IPainter  {
    void fill(Shape shape, Color color);

    void stroke(Shape shape, Color color, float wide);

    void draw(Shape shape);
}
