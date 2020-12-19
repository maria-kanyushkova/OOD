package shape;

import shape.strategies.IDrawShapeStrategy;

import java.awt.*;
import java.util.UUID;

public class Shape extends AbstractShape {
    private IDrawShapeStrategy strategy;
    private Type type = null;
    private Context context = new Context(Color.WHITE, 1.f);

    public Shape(UUID id, Type type) {
        super(id);
        this.type = type;
    }

    @Override
    public boolean isContains(Point point) {
        return strategy.isContains(point);
    }

    @Override
    public void draw(Graphics2D graphics) {
        strategy.draw(graphics);
        super.draw(graphics);
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        var newContext = new Context(this.context);
        newContext.update(context);

        this.context = newContext;
    }

    public Type getType() {
        return type;
    }

    public void setStrategy(IDrawShapeStrategy strategy) {
        this.strategy = strategy;
        this.strategy.setShapeData(this);
    }
}
