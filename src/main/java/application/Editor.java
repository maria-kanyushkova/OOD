package application;

import shape.ShapeGroup;
import shape.strategies.EllipseStrategy;
import shape.strategies.IDrawShapeStrategy;
import math.Point;
import math.Size;
import shape.IShape;
import shape.Shape;
import shape.strategies.RectangleStrategy;
import shape.strategies.TriangleStrategy;
import ui.IDrawable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Editor {
    private final List<IShape> shapes = new ArrayList<>();

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
        shapes.removeIf(shape -> selectedShapes.contains(shape));

        selectImpl(new ArrayList<>(Arrays.asList(group.getID())), false);
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

        var children = new ArrayList<IShape>();

        groups.forEach( shape -> {
            var group = (ShapeGroup) shape;
            shapes.remove(group);
            children.addAll(group.children());
        });

        shapes.addAll(children);

        selectImpl(children.stream().map(shape -> shape.getID()).collect(Collectors.toList()), false);
    }

    public void select(IShape shape, boolean isMulti) {
        selectImpl(shape != null ? new ArrayList(Arrays.asList(shape.getID())) : new ArrayList<>(), isMulti);
    }

    public IShape getInterceptingShape(Point coordinate) {
        for (IShape shape : shapes) {
            if (shape.isContains(coordinate)) {
                return shape;
            }
        }

        return null;
    }

    public List<IShape> getSelectedShapes() {
        return shapes.stream()
                .filter(shape -> shape.isSelected())
                .collect(Collectors.toList());
    }

    public List<IDrawable> getDrawableItems() {
        return shapes.stream()
                .map(shape -> (IDrawable) shape)
                .collect(Collectors.toList());
    }

    private void selectImpl(List<UUID> selectedItems, boolean isMulti) {
        if (isMulti) {
            var oldSelectedShapes = getSelectedShapes();
            oldSelectedShapes.forEach(shape -> {
                if (selectedItems.contains(shape.getID())) {
                    selectedItems.remove(shape.getID());
                } else {
                    selectedItems.add(shape.getID());
                }
            });
        }

        shapes.forEach(shape -> shape.setSelected(selectedItems.contains(shape.getID())));
    }
}
