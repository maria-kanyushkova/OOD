package application.serialize;

import shape.IShape;

import java.util.List;

public interface ISerializeStrategy {
    void serialize(List<IShape> shapes);

    List<IShape> deserialize();
}
