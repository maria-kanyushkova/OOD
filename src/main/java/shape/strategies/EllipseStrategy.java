package shape.strategies;

import shape.IShape;
import math.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseStrategy implements IDrawShapeStrategy {
    private IShape shape;

    @Override
    public void draw(Graphics2D graphics) {
        if (shape == null) {
            return;
        }

        var position = shape.getPosition();
        var size = shape.getSize();
        graphics.setColor(Color.BLACK);
        graphics.draw(new Ellipse2D.Double(position.getX(), position.getY(), size.getWidth(), size.getHeight()));
    }

    @Override
    public void setShapeData(IShape shape) {
        this.shape = shape;
    }

    @Override
    public boolean isContains(Point point) {
        var position = shape.getPosition();
        var size = shape.getSize();

        var center = new Point(position.getX() + size.getWidth() / 2, position.getY() + size.getHeight() / 2);

        return (Math.pow((point.getX() - center.getX()) * 2 / size.getWidth(), 2) + Math.pow((point.getY() - center.getY()) * 2 / size.getHeight(), 2)) <= 1;
    }
}
