package data;

import math.Point;
import math.Size;
import data.strategies.IDrawShapeStrategy;
import ui.IDrawable;

import java.awt.*;
import java.util.UUID;

public class Shape implements IShape, IDrawable {
    private Size size = new Size(100, 100);
    private Point position = new Point(0, 0);

    private final IDrawShapeStrategy strategy;
    private final UUID id = UUID.randomUUID();

    public Shape(IDrawShapeStrategy strategy) {
        this.strategy = strategy;
        this.strategy.setShapeData(this);
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public boolean isContains(Point point) {
        return strategy.isContains(point);
    }

    @Override
    public void draw(Graphics2D graphics) {
        strategy.draw(graphics);
    }
}
