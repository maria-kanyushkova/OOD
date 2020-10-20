package application;

import shape.ShapeGroup;
import shape.strategies.EllipseStrategy;
import shape.strategies.IDrawShapeStrategy;
import math.Point;
import math.Size;
import shape.IShape;
import shape.Shape;
import ui.IDrawable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Editor {
    private final List<IShape> shapes = new ArrayList<>();
    private List<UUID> selectedShapes = new ArrayList<>();

    public Editor() {
        // TODO: remove it later - need for testing
        createShape(new EllipseStrategy());
        createShape(new RectangleStrategy());
        createShape(new TriangleStrategy());
    }

    public void createShape(IDrawShapeStrategy strategy) {
        var shape = new Shape(strategy);
        shape.setPosition(new Point(100, 100));
        shape.setSize(new Size(100, 100));
        shapes.add(shape);
    }

    public void group() {
        var selectedShapes = getSelectedShapes();
        if (selectedShapes.size() <= 1) {
            // need throw exception
            System.out.println("Can't group a one shape");
            return;
        }

        var group = new ShapeGroup(selectedShapes);

        shapes.add(group);
        // удаляем исходные фигуры, чтобы фигуры не дублировались при отрисовке
        shapes.removeIf(shape -> selectedShapes.contains(shape.getID()));

        selectImpl(Collections.singletonList(group.getID()), false);
    }

    public void ungroup() {
        var selectedShapes = getSelectedShapes();

        var groups =  selectedShapes.stream()
                .filter(shape -> shape instanceof ShapeGroup)
                .collect(Collectors.toList());
        if (groups.size() == 0) {
            // need throw exception
            System.out.println("No has groups");
            return;
        }

        groups.forEach( shape -> {
            var group = (ShapeGroup) shape;
            shapes.remove(group);
            shapes.addAll(group.children());
        });
    }

    public void select(Point coordinate, boolean isMulti) {
        var selectedItems = new ArrayList<UUID>();

        for (IShape shape : shapes) {
            if (shape.isContains(coordinate)) {
                selectedItems.add(shape.getID());
            }
        }

        selectImpl(selectedItems, isMulti);
    }

    public List<IShape> getSelectedShapes() {
        return shapes.stream()
                .filter(shape -> selectedShapes.contains(shape.getID()))
                .collect(Collectors.toList());
    }

    public List<IDrawable> getDrawableItems() {
        return shapes.stream()
                .map(shape -> (IDrawable) shape)
                .collect(Collectors.toList());
    }

    private void selectImpl(List<UUID> selectedItems, boolean isMulti) {
        if (isMulti) {
            selectedItems.addAll(this.selectedShapes);
            selectedItems.removeIf(id -> this.selectedShapes.contains(id));
        }

        this.selectedShapes = selectedItems;
    }
}
