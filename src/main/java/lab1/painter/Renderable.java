package lab1.painter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Renderable implements IRenderable {
    private List<Function<Graphics2D, Void>> painter = new ArrayList<>();

    @Override
    public void render(Graphics2D graphics) {
        painter.forEach(p -> p.apply(graphics));
    }

    public void setColor(Color color) {
        painter.add((Graphics2D g2d) -> {
            g2d.setColor(color);
            return null;
        });
    }

    public void setStroke(Stroke stroke) {
        painter.add((Graphics2D g2d) -> {
            g2d.setStroke(stroke);
            return null;
        });
    }

    public void draw(Shape shape) {
        painter.add((Graphics2D g2d) -> {
            g2d.fill(shape);
            g2d.draw(shape);
            return null;
        });
    }

    public void setPaint(Color color) {
        painter.add((Graphics2D g2d) -> {
            g2d.setPaint(color);
            return null;
        });
    }
}
