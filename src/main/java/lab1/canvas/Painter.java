package lab1.canvas;

import lab1.common.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Painter implements IPainter {
    private final CanvasGraphics graphics = new CanvasGraphics();

    public CanvasGraphics getGraphics() {
        return graphics;
    }

    @Override
    public void fill(Color color) {
        graphics.setPaint(color);
    }

    @Override
    public void stroke(Color color, float wide) {
        BasicStroke stroke = new BasicStroke(wide);
        graphics.setColor(color);
        graphics.setStroke(stroke);
    }

    @Override
    public void draw(Shape shape) {
        graphics.draw(shape);
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
