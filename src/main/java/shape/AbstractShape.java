package shape;

import math.Point;
import math.Size;
import ui.IDrawable;
import ui.SelectionFrame;

import java.util.UUID;

public abstract class AbstractShape implements IShape, IDrawable {
    protected Size size = new Size(0, 0);
    protected Point position = new Point(0, 0);
    private final UUID id = UUID.randomUUID();
    private boolean selected = false;
    protected final SelectionFrame frame = new SelectionFrame();

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
        frame.setLocation(position.getX(), position.getY());
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
        frame.setSize(size.getWidth(), size.getHeight());
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        frame.setVisible(selected);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
}
