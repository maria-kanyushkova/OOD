package lab1.common.decorator;

import lab1.canvas.IPainter;
import lab1.common.IShape;

public class ShapeDecorator implements IShape {
    private IShape wrapper;

    ShapeDecorator(IShape shape) {
        this.wrapper = shape;
    }

    @Override
    public void draw(IPainter painter) {
        wrapper.draw(painter);
    }
}
