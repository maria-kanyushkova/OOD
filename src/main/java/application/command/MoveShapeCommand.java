package application.command;

import common.history.ICommand;
import shape.IShape;

import java.awt.*;

public class MoveShapeCommand implements ICommand {
    private final Point oldPosition;
    private final Point newPosition;
    private final IShape shape;

    public MoveShapeCommand(IShape shape, Point oldPosition, Point newPosition) {
        this.shape = shape;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        shape.setPosition(newPosition);
    }

    @Override
    public void reset() {
        shape.setPosition(oldPosition);
    }
}
