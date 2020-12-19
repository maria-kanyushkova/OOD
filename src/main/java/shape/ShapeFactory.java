package shape;

import math.Size;
import shape.strategies.EllipseStrategy;
import shape.strategies.IDrawShapeStrategy;
import shape.strategies.RectangleStrategy;
import shape.strategies.TriangleStrategy;

import java.awt.Point;
import java.util.List;
import java.util.UUID;


public class ShapeFactory {
    static public IShape createShape(Type type) {
        return createShapeImpl(type);
    }

    static public IShape createGroup(List<IShape> shapes) throws IllegalArgumentException {
        if (shapes.size() <= 1) {
            throw new IllegalArgumentException("Can't group a one shape or less");
        }

        return new ShapeGroup(UUID.randomUUID(), shapes);
    }

    static public IDrawShapeStrategy getStrategyByType(Type type) {
        switch (type) {
            case ELLIPSE -> {
                return new EllipseStrategy();
            }
            case TRIANGLE -> {
                return new TriangleStrategy();
            }
            case RECTANGLE -> {
                return new RectangleStrategy();
            }
            default -> {
                throw new UnknownError("Unknown a shape type");
            }
        }
    }

    static private IShape createShapeImpl(Type type) {
        var shape = new Shape(UUID.randomUUID(), type);
        shape.setStrategy(ShapeFactory.getStrategyByType(type));
        shape.setPosition(new Point(100, 100));
        shape.setSize(new Size(100, 100));

        return shape;
    }
}
