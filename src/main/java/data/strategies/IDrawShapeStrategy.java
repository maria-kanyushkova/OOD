package data.strategies;

import data.IShape;

import java.awt.*;

public interface IDrawShapeStrategy {
    void draw(Graphics2D graphics);
    void setShapeData(IShape shape);
}
