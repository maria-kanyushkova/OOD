package application.command;

import application.Editor;
import common.history.ICommand;
import shape.Context;
import shape.IShape;
import shape.Shape;
import shape.ShapeGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeShapeContextCommand implements ICommand {
    private final Context context;
    private Map<IShape, ContextStructure> contextStructureMap = new HashMap<>();

    private class ContextStructure {
        private Context newContext;
        private Context oldContext;

        public ContextStructure(Context newContext, Context oldContext) {
            this.newContext = newContext;
            this.oldContext = oldContext;
        }

        public Context getNewContext() {
            return newContext;
        }

        public Context getOldContext() {
            return oldContext;
        }
    }

    public ChangeShapeContextCommand(Context context) {
        this.context = context;
        setContextMap(Editor.getInstance().getSelectedShapes());
    }

    @Override
    public void execute() {
        contextStructureMap.forEach((shape, contexts) -> {
            ((Shape) shape).setContext(contexts.getNewContext());
        });
    }

    @Override
    public void reset() {
        contextStructureMap.forEach((shape, contexts) -> {
            ((Shape) shape).setContext(contexts.getOldContext());
        });
    }

    private void setContextMap(List<IShape> shapes) {
        shapes.forEach(shape -> {
            if (shape instanceof Shape) {
                contextStructureMap.put(shape, new ContextStructure(context, ((Shape) shape).getContext()));
            } else if (shape instanceof ShapeGroup) {
                setContextMap(((ShapeGroup) shape).children());
            }
        });
    }
}
