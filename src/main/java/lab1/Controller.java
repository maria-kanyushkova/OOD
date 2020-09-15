package lab1;

import lab1.common.IShape;
import lab1.canvas.ICanvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private List<IShape> shapes = new ArrayList<>();

    public ArrayList<String> getShapeInfo() {
        ArrayList<String> result = new ArrayList<>();
        for (IShape shape : shapes) {
            result.add(getClassName(shape) + ": " + "P=" + shape.getPerimeter() + "; S=" + shape.getArea());
        }
        return result;
    }

    public String getClassName(IShape shape) {
        String expression = shape.getClass().toString();
        ArrayList<String> expressionArray = new ArrayList<>();
        Collections.addAll(expressionArray, expression.split("\\."));
        String className = expressionArray.get(expressionArray.size() - 1);
        return className.toUpperCase();
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
