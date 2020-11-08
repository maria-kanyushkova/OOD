package shape.strategies;

import shape.IShape;
import shape.Shape;

import java.awt.*;

public abstract class AbstractDrawShapeStrategy implements IDrawShapeStrategy {
    protected Shape shape;

    @Override
    public void draw(Graphics2D graphics) {
        if (shape == null) {
            return;
        }

        var context = shape.getContext();
        var component = createComponent();

        // fill
        graphics.setColor(context.getFillColor());
        graphics.fill(component);

        // stroke
        graphics.setColor(context.getOutlineColor());
        graphics.setStroke(new BasicStroke(context.getStroke()));
        graphics.draw(component);
    }

    @Override
    public void setShapeData(IShape shape) {
        this.shape = (Shape) shape;
    }

    protected abstract java.awt.Shape createComponent();
}
