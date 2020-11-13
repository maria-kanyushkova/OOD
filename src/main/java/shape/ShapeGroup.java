package shape;

import math.Size;
import ui.IDrawable;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapeGroup extends AbstractShape {
    private final List<IShape> shapes;

    public ShapeGroup(List<IShape> shapes) {
        this.shapes = shapes;
        updatePhysicAttributes();
    }

    @Override
    public void setPosition(Point position) {
        var deltaX = position.x - this.position.x;
        var deltaY = position.y - this.position.y;

        shapes.forEach(shape -> {
            var oldPosition = shape.getPosition();
            shape.setPosition(new Point(oldPosition.x + deltaX, oldPosition.y + deltaY));
        });

        super.setPosition(position);
    }

    @Override
    public void setSize(Size size) {
        var deltaWidth = size.getWidth() / this.size.getWidth();
        var deltaHeight = size.getHeight() / this.size.getHeight();

        shapes.forEach(shape -> {
            var oldSize = shape.getSize();
            shape.setSize(new Size(oldSize.getWidth() * deltaWidth, oldSize.getHeight() * deltaHeight));
        });

        super.setSize(size);
    }

    @Override
    public boolean isContains(Point point) {
        for (IShape shape : shapes) {
            if (shape.isContains(point)) {
                return true;
            }
        }
        return false;
    }

    public List<IShape> children() {
        return shapes;
    }

    @Override
    public void draw(Graphics2D graphics) {
        shapes.forEach(shape -> {
            if (shape instanceof IDrawable) {
                ((IDrawable) shape).draw(graphics);
            }
        });
        super.draw(graphics);
    }

    private void updatePhysicAttributes() {
        AtomicInteger left = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger top = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger right = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger bottom = new AtomicInteger(Integer.MIN_VALUE);

        shapes.forEach(shape -> {
            var position = shape.getPosition();
            var size = shape.getSize();
            left.set(Math.min(position.x, left.get()));
            top.set(Math.min(position.y, top.get()));
            right.set(Math.max(position.x + size.getWidth(), right.get()));
            bottom.set(Math.max(position.y + size.getHeight(), bottom.get()));
        });

        super.setPosition(new Point(left.get(), top.get()));
        super.setSize(new Size(right.get() - left.get(), bottom.get() - top.get()));
    }
}
