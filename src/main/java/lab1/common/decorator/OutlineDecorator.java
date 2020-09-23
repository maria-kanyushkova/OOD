package lab1.common.decorator;

import lab1.canvas.IPainter;
import lab1.common.IShape;

import java.awt.*;

public class OutlineDecorator extends ShapeDecorator {
    final private Color outlineColor;

    public OutlineDecorator(IShape shape, Color outlineColor) {
        super(shape);
        this.outlineColor = outlineColor;
    }

    public void draw(IPainter painter) {
        painter.stroke(outlineColor, 2.f);
        super.draw(painter);
    }
}
