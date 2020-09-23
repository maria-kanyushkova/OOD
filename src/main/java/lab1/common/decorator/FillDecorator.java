package lab1.common.decorator;

import lab1.canvas.IPainter;
import lab1.common.IShape;

import java.awt.*;

public class FillDecorator extends ShapeDecorator {
    final private Color fillColor;

    public FillDecorator(IShape shape, Color fillColor) {
        super(shape);
        this.fillColor = fillColor;
    }

    public void draw(IPainter painter) {
        painter.fill(this.fillColor);
        super.draw(painter);
    }
}
