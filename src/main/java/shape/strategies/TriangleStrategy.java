package shape.strategies;

import shape.IShape;
import math.Point;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

public class TriangleStrategy implements IDrawShapeStrategy {
    private IShape shape;

    @Override
    public void draw(Graphics2D graphics) {
        if (shape == null) {
            return;
        }

        var points = getVertices();

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

    @Override
    public boolean isContains(Point point) {
        var points = getVertices();
        var vertex1 = points.get(0);
        var vertex2 = points.get(1);
        var vertex3 = points.get(2);

        int cond1 = (vertex1.getX() - point.getX()) * (vertex2.getY() - vertex1.getY()) - (vertex2.getX() - vertex1.getX()) * (vertex1.getY() - point.getY());
        int cond2 = (vertex2.getX() - point.getX()) * (vertex3.getY() - vertex2.getY()) - (vertex3.getX() - vertex2.getX()) * (vertex2.getY() - point.getY());
        int cond3 = (vertex3.getX() - point.getX()) * (vertex1.getY() - vertex3.getY()) - (vertex1.getX() - vertex3.getX()) * (vertex3.getY() - point.getY());

        return (cond1 >= 0 && cond2 >= 0 && cond3 >= 0) || (cond1 <= 0 && cond2 <= 0 && cond3 <= 0);
    }

    private List<Point> getVertices() {
        var position = shape.getPosition();
        var size = shape.getSize();

        var vertex1 = new Point(position.getX() + size.getWidth() / 2, position.getY());
        var vertex2 = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        var vertex3 = new Point(position.getX(), position.getY() + size.getHeight());

        return Arrays.asList(vertex1, vertex2, vertex3);
    }
}
