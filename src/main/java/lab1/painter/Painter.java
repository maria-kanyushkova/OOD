package lab1.painter;

import lab1.common.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Painter implements IPainter {
    private final Renderable renderable = new Renderable();

    public Renderable getRenderable() {
        return renderable;
    }

    @Override
    public void fill(Color color) {
        renderable.setPaint(color);
    }

    @Override
    public void stroke(Color color, float wide) {
        BasicStroke stroke = new BasicStroke(wide);
        renderable.setColor(color);
        renderable.setStroke(stroke);
    }

    @Override
    public void draw(Shape shape) {
        renderable.draw(shape);
    }

    @Override
    public Shape createLine(Point from, Point to) {
        return new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY());
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
