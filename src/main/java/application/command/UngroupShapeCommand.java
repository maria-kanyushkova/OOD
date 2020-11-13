package application.command;

import application.Editor;
import common.history.ICommand;
import shape.IShape;
import shape.ShapeGroup;

import java.util.List;
import java.util.stream.Collectors;


public class UngroupShapeCommand implements ICommand {
    private final List<IShape> groups;

    public UngroupShapeCommand(List<IShape> shapes) {
        this.groups = shapes.stream()
                .filter(shape -> shape instanceof ShapeGroup)
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        if (groups.size() == 0) {
            return;
        }
        groups.forEach((group) -> Editor.getInstance().ungroup(group));
    }

    @Override
    public void reset() {
        if (groups.size() == 0) {
            return;
        }
        groups.forEach((group) -> Editor.getInstance().group(group));
    }
}
