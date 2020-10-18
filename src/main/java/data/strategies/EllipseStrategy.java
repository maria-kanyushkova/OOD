package data.strategies;

import data.IShape;

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
        graphics.draw(new Ellipse2D.Double(position.getX(), position.getY(), size.getWidth() / 2.f, size.getHeight() / 2.f));
    }

    @Override
    public void setShapeData(IShape shape) {
        this.shape = shape;
    }
}
