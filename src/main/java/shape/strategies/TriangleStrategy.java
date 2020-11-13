package shape.strategies;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;

public class TriangleStrategy extends AbstractDrawShapeStrategy {
    @Override
    protected java.awt.Shape createComponent() {
        var points = getVertices();

        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX(), points.get(0).getY());
        points.stream().skip(1).forEach(point -> path.lineTo(point.getX(), point.getY()));
        path.closePath();

        return path;
    }

    @Override
    public boolean isContains(Point point) {
        var points = getVertices();
        var vertex1 = points.get(0);
        var vertex2 = points.get(1);
        var vertex3 = points.get(2);

        var cond1 = (vertex1.getX() - point.getX()) * (vertex2.getY() - vertex1.getY()) - (vertex2.getX() - vertex1.getX()) * (vertex1.getY() - point.getY());
        var cond2 = (vertex2.getX() - point.getX()) * (vertex3.getY() - vertex2.getY()) - (vertex3.getX() - vertex2.getX()) * (vertex2.getY() - point.getY());
        var cond3 = (vertex3.getX() - point.getX()) * (vertex1.getY() - vertex3.getY()) - (vertex1.getX() - vertex3.getX()) * (vertex3.getY() - point.getY());

        return (cond1 >= 0 && cond2 >= 0 && cond3 >= 0) || (cond1 <= 0 && cond2 <= 0 && cond3 <= 0);
    }

    private List<Point> getVertices() {
        var position = shape.getPosition();
        var size = shape.getSize();

        var vertex1 = new Point(position.x + size.getWidth() / 2, position.y);
        var vertex2 = new Point(position.x + size.getWidth(), position.y + size.getHeight());
        var vertex3 = new Point(position.x, position.y + size.getHeight());

        return Arrays.asList(vertex1, vertex2, vertex3);
    }
}
