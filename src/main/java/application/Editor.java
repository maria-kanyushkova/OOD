package application;

import shape.ShapeGroup;
import shape.IShape;
import ui.IDrawable;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public final class Editor {
    private static volatile Editor instance;

    private final List<IShape> shapes = new ArrayList<>();

    private Editor() {}

    public static Editor getInstance() {
        if (instance == null) {
            synchronized (Editor.class) {
                if (instance == null) {
                    instance = new Editor();
                }
            }
        }
        return instance;
    }

    public void appendShape(IShape shape) {
        shapes.add(shape);

        selectImpl(new ArrayList<>(Arrays.asList(shape.getID())), false);
    }

    public void removeShape(UUID uuid) {
        shapes.removeIf(shape -> shape.getID().equals(uuid));
    }

    public void group(IShape group) {
        appendShape(group);

        if (group instanceof ShapeGroup) {
            var children = ((ShapeGroup) group).children();
            // удаляем исходные фигуры, чтобы фигуры не дублировались при отрисовке
            shapes.removeIf(shape -> children.contains(shape));
        }
    }

    public void ungroup(IShape group) {
        if (group instanceof ShapeGroup) {
            var children = ((ShapeGroup) group).children();

            shapes.remove(group);
            shapes.addAll(children);
            selectImpl(children.stream().map(shape -> shape.getID()).collect(Collectors.toList()), false);
        }
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
