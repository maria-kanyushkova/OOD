package shape.strategies;

import shape.IShape;
import math.Point;

import java.awt.Graphics2D;

public interface IDrawShapeStrategy {
    void draw(Graphics2D graphics);

    void setShapeData(IShape shape);

    boolean isContains(Point point);
}
