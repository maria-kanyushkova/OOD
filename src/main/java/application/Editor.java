package application;

import data.strategies.EllipseStrategy;
import data.strategies.IDrawShapeStrategy;
import data.strategies.RectangleStrategy;
import data.strategies.TriangleStrategy;
import math.Point;
import math.Size;
import data.IShape;
import data.Shape;
import ui.IDrawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Editor implements IDrawable {
    private List<IShape> shapes = new ArrayList<>();

    public Editor() {
        // TODO: remove it later - need for testing
        createShape(new EllipseStrategy());
        createShape(new RectangleStrategy());
        createShape(new TriangleStrategy());
    }

    public void createShape(IDrawShapeStrategy strategy) {
        var shape = new Shape(strategy);
        shape.setPosition(new Point(100, 100));
        shape.setSize(new Size(100, 100));
        shapes.add(shape);
    }

    @Override
    public void draw(Graphics2D graphics) {
        shapes.forEach(shape -> {
            if (shape instanceof IDrawable) {
                ((IDrawable) shape).draw(graphics);
            }
        });
    }
}
