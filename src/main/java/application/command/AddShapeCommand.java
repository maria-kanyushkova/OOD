package application.command;

import common.history.ICommand;
import application.Editor;
import shape.ShapeFactory;
import shape.Type;

public class AddShapeCommand implements ICommand {
    private final Type shapeType;
    private final Editor editor;

    public AddShapeCommand(Editor editor, Type shapeType) {
        this.editor = editor;
        this.shapeType = shapeType;
    }

    @Override
    public void execute() {
        try {
            editor.appendShape(ShapeFactory.createShape(shapeType));
        } catch (UnknownError error) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void reset() {
        // will implement in lab 4
    }
}
