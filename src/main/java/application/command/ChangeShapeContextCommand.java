package application.command;

import application.Editor;
import common.history.ICommand;
import shape.Context;
import shape.IShape;
import shape.Shape;
import shape.ShapeGroup;

import java.util.List;

public class ChangeShapeContextCommand implements ICommand {
    private final Context context;

    public ChangeShapeContextCommand(Context context) {
        this.context = context;
    }

    @Override
    public void execute() {
        var shapes = Editor.getInstance().getSelectedShapes();
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
