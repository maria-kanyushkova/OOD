package lab1.common;

import lab1.canvas.ICanvasDrawable;

import java.awt.*;

public interface IShape extends ICanvasDrawable {
    int getArea();

    int getPerimeter();

    Color getOutlineColor();

    String toString();
}
