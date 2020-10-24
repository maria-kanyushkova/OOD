package shape;

import math.Point;
import math.Size;
import ui.IDrawable;
import ui.SelectionFrame;

import java.awt.*;
import java.util.UUID;

public abstract class AbstractShape implements IShape, IDrawable {
    protected Size size = new Size(0, 0);
    protected Point position = new Point(0, 0);
    private final UUID id = UUID.randomUUID();
    private boolean selected = false;
    protected final SelectionFrame frame = new SelectionFrame();

    @Override
    public final UUID getID() {
        return id;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
        frame.setLocation(position.getX(), position.getY());
    }

    @Override
    public final Point getPosition() {
        return position;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
        frame.setSize(size.getWidth(), size.getHeight());
    }

    @Override
    public final Size getSize() {
        return size;
    }

    @Override
    public final void setSelected(boolean selected) {
        this.selected = selected;
        frame.setVisible(selected);
    }

    @Override
    public final boolean isSelected() {
        return selected;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (isSelected()) {
            frame.paint(graphics);
        }
    }
}
