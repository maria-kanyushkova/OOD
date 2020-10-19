package data;

import math.Point;
import math.Size;

public interface IShape {
    void setPosition(Point position);
    Point getPosition();

    void setSize(Size size);
    Size getSize();

    boolean isContains(Point point);
}
