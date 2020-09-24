package lab1.painter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Renderable implements IRenderable {
    private List<Function<Graphics, Void>> painter = new ArrayList<>();

    @Override
    public void render(Graphics graphics) {
        painter.forEach(p -> p.apply(graphics));
    }

    public void setColor(Color color) {
        painter.add((Graphics g2d) -> {
            g2d.setColor(color);
            return null;
        });
    }

    public void setStroke(Stroke stroke) {
        painter.add((Graphics g2d) -> {
            // g2d.setStroke(stroke);
            return null;
        });
    }

    public void draw(Shape shape) {
        painter.add((Graphics g2d) -> {
            // g2d.draw(shape);
            return null;
        });
    }

    public void setPaint(Color color) {
        painter.add((Graphics g2d) -> {
            // g2d.setPaint(color);
            return null;
        });
    }
}
