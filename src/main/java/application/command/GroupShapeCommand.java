package application.command;

import application.Editor;
import common.history.ICommand;
import shape.IShape;
import shape.ShapeFactory;

import java.util.List;


public class GroupShapeCommand implements ICommand {

    private final IShape group;

    public GroupShapeCommand(List<IShape> shapes) {
        this.group = ShapeFactory.createGroup(shapes);
    }

    @Override
    public void execute() {
        Editor.getInstance().group(group);
    }

    @Override
    public void reset() {
        Editor.getInstance().ungroup(group);
    }
}
