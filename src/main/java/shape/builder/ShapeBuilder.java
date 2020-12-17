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

    public ShapeBuilder setSize(int width, int height) {
        size = new Size(width, height);
        return this;
    }

    public ShapeBuilder setPosition(int x, int y) {
        position = new Point(x, y);
        return this;
    }

    public ShapeBuilder setId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public ShapeBuilder setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public ShapeBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public ShapeBuilder setContext(Context context) {
        this.context = context;
        return this;
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
