package lab1;

import lab1.common.IShape;
import lab1.canvas.ICanvas;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<IShape> shapes = new ArrayList<>();

    public String getInfoAboutFigureWithMaxArea() {
        double area = 0;
        IShape figure = null;
        for (IShape shape : shapes) {
            if (area <= shape.getArea()) {
                area = shape.getArea();
                figure = shape;
            }
        }
        return figure != null ? figure.toString() : "";
    }

    public String getInfoAboutFigureWithMinPerimeter() {
        double perimeter = Double.MAX_VALUE;
        IShape figure = null;
        for (IShape shape : shapes) {
            if (perimeter >= shape.getPerimeter()) {
                perimeter = shape.getPerimeter();
                figure = shape;
            }
        }
        return figure != null ? figure.toString() : "";
    }

    public void appendShape(IShape shape) {
        shapes.add(shape);
    }

    public void draw(ICanvas canvas) {
        shapes.forEach(figure -> figure.draw(canvas));
    }

    public List<IShape> getShapes() {
        return shapes;
    }
}
