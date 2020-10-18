package data.strategies;

import data.IShape;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;

public class TriangleStrategy implements IDrawShapeStrategy {
    private IShape shape;

    @Override
    public void draw(Graphics2D graphics) {
        if (shape == null) {
            return;
        }

        var position = shape.getPosition();
        var size = shape.getSize();

        var vertex1 = new Point(position.getX() + size.getWidth() / 2, position.getY());
        var vertex2 = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        var vertex3 = new Point(position.getX(), position.getY() + size.getHeight());

        var points = Arrays.asList(vertex1, vertex2, vertex3);

        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX(), points.get(0).getY());
        points.stream().skip(1).forEach(point -> path.lineTo(point.getX(), point.getY()));
        path.closePath();

        graphics.draw(path);
    }

    @Override
    public void setShapeData(IShape shape) {
        this.shape = shape;
    }
}
