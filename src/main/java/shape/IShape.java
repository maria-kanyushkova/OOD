package shape;

import math.Point;
import math.Size;

import java.util.UUID;

public interface IShape {
    UUID getID();

    void setPosition(Point position);
    Point getPosition();

    void setSize(Size size);
    Size getSize();

    boolean isContains(Point point);

    void setSelected(boolean selected);
    boolean isSelected();
}
