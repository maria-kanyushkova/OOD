package shape;

import math.Point;
import math.Size;
import shape.strategies.EllipseStrategy;
import shape.strategies.IDrawShapeStrategy;
import shape.strategies.RectangleStrategy;
import shape.strategies.TriangleStrategy;

public class ShapeFactory {
    static public IShape createShape(Type type) {
        switch (type) {
            case ELLIPSE -> {
                return createShapeImpl(new EllipseStrategy());
            }
            case TRIANGLE -> {
                return createShapeImpl(new TriangleStrategy());
            }
            case RECTANGLE -> {
                return createShapeImpl(new RectangleStrategy());
            }
            default -> {
                throw new UnknownError("Unknown a shape type");
            }
        }
    }

    static private IShape createShapeImpl(IDrawShapeStrategy strategy) {
        var shape = new Shape(strategy);
        shape.setPosition(new Point(100, 100));
        shape.setSize(new Size(100, 100));

        return shape;
    }
}
