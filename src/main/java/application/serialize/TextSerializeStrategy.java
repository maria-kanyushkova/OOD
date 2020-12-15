package application.serialize;

import common.FileManager;
import shape.IShape;
import shape.Shape;
import shape.ShapeGroup;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * File format `type,id,x,y,width,height,selected,fillColor,outlineColor,stroke,group_id`
 */
public class TextSerializeStrategy implements ISerializeStrategy {
    private final String fileName = "shapes.txt";

    @Override
    public void serialize(List<IShape> shapes) {
        try {
            if (FileManager.isFileExist(fileName)) {
                var oldFile = FileManager.getFileByPath(fileName);
                oldFile.delete();
            }

            var file = FileManager.create(fileName);
            serializeImpl(file, shapes, null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<IShape> deserialize() {
        return null;
    }

    private void serializeImpl(File file, List<IShape> shapes, UUID groupUuid) throws IOException {
        for (var shape: shapes) {
            if (shape instanceof ShapeGroup) {
                serializeImpl(file, ((ShapeGroup) shape).children(), shape.getID());
            } else if (shape instanceof Shape) {
                var context = ((Shape) shape).getContext();
                String content = shape.getType().toString() + "," +
                        shape.getID() + "," +
                        shape.getPosition().x + "," +
                        shape.getPosition().y + "," +
                        shape.getSize().getWidth() + "," +
                        shape.getSize().getHeight() + "," +
                        shape.isSelected() + "," +
                        context.getFillColor() + "," +
                        context.getOutlineColor() + "," +
                        context.getStroke() + "," +
                        groupUuid + '\n';

                FileManager.write(file, content);
            }
        }
    }
}
