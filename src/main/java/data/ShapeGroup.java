package data;

import math.Point;
import math.Size;
import ui.IDrawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapeGroup implements IShape, IDrawable {
    private final ArrayList<IShape> shapes;
    private Size size = new Size(0, 0);
    private Point position = new Point(0, 0);

    public ShapeGroup(ArrayList<IShape> shapes) {
        this.shapes = shapes;
        updatePhysicAttributes();
    }

    @Override
    public void setPosition(Point position) {
        var deltaX = position.getX() - this.position.getX();
        var deltaY = position.getY() / this.position.getY();

        shapes.forEach(shape -> {
            var oldPosition = shape.getPosition();
            shape.setPosition(new Point(oldPosition.getX() + deltaX, oldPosition.getY() + deltaY));
        });

        setPositionImpl(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setSize(Size size) {
        var deltaWidth = size.getWidth() / this.size.getWidth();
        var deltaHeight = size.getHeight() / this.size.getHeight();

        shapes.forEach(shape -> {
            var oldSize = shape.getSize();
            shape.setSize(new Size(oldSize.getWidth() * deltaWidth, oldSize.getHeight() * deltaHeight));
        });

        setSizeImpl(size);
    }

    @Override
    public Size getSize() {
        return size;
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

    @Override
    public void draw(Graphics2D graphics) {
        shapes.forEach(shape -> {
            if (shape instanceof IDrawable) {
                ((IDrawable) shape).draw(graphics);
            }
        });
    }

    private void updatePhysicAttributes() {
        AtomicInteger left = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger top = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger right = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger bottom = new AtomicInteger(Integer.MIN_VALUE);

        shapes.forEach(shape -> {
            var position = shape.getPosition();
            var size = shape.getSize();
            left.set(Math.min(position.getX(), left.get()));
            top.set(Math.min(position.getY(), top.get()));
            right.set(Math.max(position.getX() + size.getWidth(), top.get()));
            bottom.set(Math.max(position.getY() + size.getHeight(), top.get()));
        });

        setPositionImpl(new Point(left.get(), top.get()));
        setSizeImpl(new Size(right.get() - left.get(), bottom.get() - top.get()));
    }

    private void setSizeImpl(Size size) {
        this.size = size;
    }

    private void setPositionImpl(Point position) {
        this.position = position;
    }
}
