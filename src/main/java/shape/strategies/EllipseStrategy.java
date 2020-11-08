package shape.strategies;

import math.Point;

import java.awt.geom.Ellipse2D;

public class EllipseStrategy extends AbstractDrawShapeStrategy {
    @Override
    protected java.awt.Shape createComponent() {
        var position = shape.getPosition();
        var size = shape.getSize();

        return new Ellipse2D.Double(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public boolean isContains(Point point) {
        var position = shape.getPosition();
        var size = shape.getSize();

        var center = new Point(position.getX() + size.getWidth() / 2, position.getY() + size.getHeight() / 2);

        return (Math.pow((point.getX() - center.getX()) * 2 / size.getWidth(), 2) + Math.pow((point.getY() - center.getY()) * 2 / size.getHeight(), 2)) <= 1;
    }
}
