package lab1;

import lab1.common.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeList {
    private List<IShape> shapes = new ArrayList<>();

    public ArrayList<String> getShapeInfo() {
        ArrayList<String> result = new ArrayList<>();
        for (IShape shape : shapes) {
            result.add(shape.getName() + ": " + "P=" + shape.getPerimeter() + "; S=" + shape.getArea());
        }
        return result;
    }

    public void push(IShape shape) {
        shapes.add(shape);
    }

    public List<IShape> getShapes() {
        return shapes;
    }
}
