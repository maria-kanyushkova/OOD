package shape;

import math.Point;
import shape.strategies.IDrawShapeStrategy;

import java.awt.*;

public class Shape extends AbstractShape {
    private final IDrawShapeStrategy strategy;


    public Shape(IDrawShapeStrategy strategy) {
        this.strategy = strategy;
        this.strategy.setShapeData(this);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        frame.setVisible(selected);
    }

    @Override
    public boolean isContains(Point point) {
        return strategy.isContains(point);
    }

    @Override
    public void draw(Graphics2D graphics) {
        strategy.draw(graphics);
        frame.paint(graphics);
    }
}
