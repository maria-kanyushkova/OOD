package lab1.common.decorator;

import lab1.painter.IPainter;
import lab1.common.IShape;

import java.awt.*;

public class ShapeDecorator implements IShape {
    private final IShape wrapper;

    ShapeDecorator(IShape shape) {
        this.wrapper = shape;
    }

    @Override
    public String getName() {
        return wrapper.getName();
    }

    @Override
    public int getArea() {
        return wrapper.getArea();
    }

    @Override
    public int getPerimeter() {
        return wrapper.getPerimeter();
    }

    @Override
    public void draw(IPainter painter) {
        wrapper.draw(painter);
    }

    @Override
    public Shape getShape() {
        return wrapper.getShape();
    }
}
