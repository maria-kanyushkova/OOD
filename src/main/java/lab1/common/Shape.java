package lab1.common;

import lab1.Utils;

import java.awt.*;

public abstract class Shape implements IShape {
    public abstract int getArea();

    public abstract int getPerimeter();

    public abstract Color getOutlineColor();

    public String toString() {
        return "Площадь фигуры: " + getArea() + "\n" +
                "Периметр фигуры: " + getPerimeter() + "\n" +
                "Цвет обводки: " + Utils.colorToString(getOutlineColor()) + "\n";
    }
}
