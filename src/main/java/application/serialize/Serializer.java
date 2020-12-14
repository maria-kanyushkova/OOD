package application.serialize;

import application.Editor;

public class Serializer {
    private ISerializeStrategy strategy;

    public void setStrategy(ISerializeStrategy strategy) {
        this.strategy = strategy;
    }

    public void save() {
        strategy.serialize(Editor.getInstance().getShapes());
    }

    public void load() {
        Editor.getInstance().setShapes(strategy.deserialize());
    }
}
