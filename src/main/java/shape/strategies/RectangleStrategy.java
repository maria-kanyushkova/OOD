package shape.strategies;

import shape.IShape;
import math.Point;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectangleStrategy implements IDrawShapeStrategy {
    private IShape shape;

    @Override
    public void draw(Graphics2D graphics) {
        if (shape == null) {
            return;
        }

        var position = shape.getPosition();
        var size = shape.getSize();
        graphics.draw(new Rectangle2D.Double(position.getX(), position.getY(), size.getWidth(), size.getHeight()));
    }

    @Override
    public void setShapeData(IShape shape) {
        this.shape = shape;
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
