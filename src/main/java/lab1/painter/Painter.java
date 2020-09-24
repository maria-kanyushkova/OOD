package lab1.painter;

import lab1.common.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Painter implements IPainter {
    private final Renderable renderable = new Renderable();

    public Renderable getRenderable() {
        return renderable;
    }

    @Override
    public void fill(Color color) {
        renderable.setColor(color);
    }

    @Override
    public void stroke(Color color, float wide) {
        BasicStroke bs = new BasicStroke(wide, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        renderable.setStroke(bs);
        renderable.setPaint(color);
    }

    @Override
    public void draw(Shape shape) {
        renderable.draw(shape);
    }

    @Override
    public Shape createPolygon(List<Point> points) {
        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX(), points.get(0).getY());
        points.stream().skip(1).forEach(point -> path.lineTo(point.getX(), point.getY()));
        path.closePath();

        return path;
    }

    @Override
    public Shape createRectangle(Point position, int width, int height) {
        return new Rectangle2D.Double(position.getX(), position.getY(), width, height);
    }

    @Override
    public Shape createEllipse(Point center, double radius) {
        return new Ellipse2D.Double(center.getX(), center.getY(), radius, radius);
    }
}
