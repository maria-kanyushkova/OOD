package application.command;

import common.history.ICommand;
import application.Editor;
import shape.IShape;
import shape.ShapeFactory;
import shape.Type;

public class AddShapeCommand implements ICommand {
    private final IShape shape;

    public AddShapeCommand(Type shapeType) {
        shape = ShapeFactory.createShape(shapeType);
    }

    @Override
    public void execute() {
        Editor.getInstance().appendShape(shape);
    }

    @Override
    public void reset() {
        Editor.getInstance().removeShape(shape.getID());
    }
}
