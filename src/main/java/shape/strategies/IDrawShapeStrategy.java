package shape.strategies;

import shape.IShape;

import java.awt.*;

public interface IDrawShapeStrategy {
    void draw(Graphics2D graphics);

    void setShapeData(IShape shape);

    boolean isContains(Point point);
}
