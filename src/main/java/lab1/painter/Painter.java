package lab1.painter;

import java.awt.*;

public class Painter implements IPainter {
    private final Renderable renderable = new Renderable();

    public Renderable getRenderable() {
        return renderable;
    }

    @Override
    public void fill(Shape shape, Color color) {
        renderable.setColor(color);
        renderable.fill(shape);
    }

    @Override
    public void stroke(Shape shape, Color color, float wide) {
        BasicStroke bs = new BasicStroke(wide, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        renderable.setStroke(bs);
        renderable.setColor(color);
        renderable.draw(shape);
    }

    @Override
    public void draw(Shape shape) {
        renderable.draw(shape);
    }
}
