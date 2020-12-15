package shape.builder;

import math.Size;
import shape.*;
import shape.Shape;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ShapeBuilder {
    private Size size;
    private Point position;
    private UUID id;
    private boolean selected;
    private Type type;
    private Context context;

    public void setSize(int width, int height) {
        size = new Size(width, height);
    }

    public void setPosition(int x, int y) {
        position = new Point(x, y);
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IShape build() {
        Shape shape = new Shape(id, type);
        shape.setPosition(position);
        shape.setSize(size);
        shape.setSelected(selected);
        shape.setContext(context);
        shape.setStrategy(ShapeFactory.getStrategyByType(type));

        return shape;
    }

    public IShape buildGroup(List<IShape> shapes) {
        ShapeGroup group = new ShapeGroup(id, shapes);
        group.setPosition(position);
        group.setSize(size);
        group.setSelected(selected);

        return group;
    }
}
