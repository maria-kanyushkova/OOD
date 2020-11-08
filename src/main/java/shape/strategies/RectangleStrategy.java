package shape.strategies;

import math.Point;

import java.awt.geom.Rectangle2D;

public class RectangleStrategy extends AbstractDrawShapeStrategy {
    @Override
    protected java.awt.Shape createComponent() {
        var position = shape.getPosition();
        var size = shape.getSize();

        return new Rectangle2D.Double(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public boolean isContains(Point point) {
        var position = shape.getPosition();
        var size = shape.getSize();
        var leftTopPoint = new Point(position.getX(), position.getY());
        var rightBottomPoint = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());

        return point.getX() >= leftTopPoint.getX() && point.getX() <= rightBottomPoint.getX() && point.getY() >= leftTopPoint.getY() && point.getY() <= rightBottomPoint.getY();
    }
}
