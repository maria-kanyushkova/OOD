package data.strategies;

import data.IShape;

import java.awt.*;
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
}
