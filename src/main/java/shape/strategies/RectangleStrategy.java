package shape.strategies;

import java.awt.*;
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
        var leftTopPoint = new Point(position.x, position.y);
        var rightBottomPoint = new Point(position.x + size.getWidth(), position.y + size.getHeight());

        return point.getX() >= leftTopPoint.getX() && point.getX() <= rightBottomPoint.getX() && point.getY() >= leftTopPoint.getY() && point.getY() <= rightBottomPoint.getY();
    }
}
