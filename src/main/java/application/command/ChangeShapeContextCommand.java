package application.command;

import application.Editor;
import common.history.ICommand;
import shape.Context;
import shape.IShape;
import shape.Shape;
import shape.ShapeGroup;

import java.util.List;

public class ChangeShapeContextCommand implements ICommand {
    private final Editor editor;
    private final Context context;

    public ChangeShapeContextCommand(Editor editor, Context context) {
        this.editor = editor;
        this.context = context;
    }

    @Override
    public void execute() {
        var shapes = editor.getSelectedShapes();
        updateContext(shapes);
    }

    @Override
    public void reset() {
        // will implement in lab 4
    }

    private void updateContext(List<IShape> shapes) {
        shapes.forEach(shape -> {
            if (shape instanceof Shape) {
                ((Shape) shape).updateContext(context);
            } else if (shape instanceof ShapeGroup) {
                updateContext(((ShapeGroup) shape).children());
            }
        });
    }
}
