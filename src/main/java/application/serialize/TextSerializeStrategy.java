package application.serialize;

import common.FileManager;
import shape.*;
import shape.Shape;
import shape.builder.ShapeBuilder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * File format `type,id,x,y,width,height,selected,fillColor,outlineColor,stroke,group_id`
 */
public class TextSerializeStrategy implements ISerializeStrategy {
    private final String fileName = "src/main/resources/shapes.txt";

    @Override
    public void serialize(List<IShape> shapes) {
        try {
            if (FileManager.isFileExist(fileName)) {
                var oldFile = FileManager.getFileByPath(fileName);
                oldFile.delete();
            }

            var file = FileManager.create(fileName);
            Collections.reverse(shapes);
            serializeImpl(file, shapes, null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<IShape> deserialize() {
        var shapes = new ArrayList<IShape>();

        try {
            if (FileManager.isFileExist(fileName)) {
                var lines = FileManager.read(FileManager.getFileByPath(fileName));
                Collections.reverse(lines);
                shapes.addAll(deserializeImpl(lines));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return shapes;
    }

    private List<IShape> deserializeImpl(List<String> lines) {
        var shapes = new ArrayList<IShape>();
        var groupItems = new ArrayList<IShape>();

        for (var line: lines) {
            var data = Arrays.asList(line.split(","));
            var builder = new ShapeBuilder();

            var type = Type.fromString(data.get(0));
            var groupId = data.get(7);
            IShape shape;

            if (type != null) {
                var context = new Context();
                context.setFillColor(new Color(Integer.parseInt(data.get(8))));
                context.setOutlineColor(new Color(Integer.parseInt(data.get(9))));
                context.setStroke(Float.parseFloat(data.get(10)));

                shape = builder
                        .setType(type)
                        .setId(data.get(1))
                        .setPosition(Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)))
                        .setSize(Integer.parseInt(data.get(4)), Integer.parseInt(data.get(5)))
                        .setSelected(Boolean.parseBoolean(data.get(6)))
                        .setContext(context)
                        .build();

            } else {
                shape = builder
                        .setId(data.get(1))
                        .setPosition(Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)))
                        .setSize(Integer.parseInt(data.get(4)), Integer.parseInt(data.get(5)))
                        .setSelected(Boolean.parseBoolean(data.get(6)))
                        .buildGroup(groupItems);
                groupItems.clear();
            }

            if (groupId.contains("null")) {
                shapes.add(shape);
            } else {
                groupItems.add(shape);
            }

        }
        return shapes;
    }

    private void serializeImpl(File file, List<IShape> shapes, UUID groupUuid) throws IOException {
        for (var shape: shapes) {
            if (shape instanceof ShapeGroup) {
                String content = "null" + "," + // 0
                        shape.getID() + "," +                       // 1
                        shape.getPosition().x + "," +               // 2
                        shape.getPosition().y + "," +               // 3
                        shape.getSize().getWidth() + "," +          // 4
                        shape.getSize().getHeight() + "," +         // 5
                        shape.isSelected() + "," +                  // 6
                        groupUuid + '\n';                           // 7
                FileManager.write(file, content);
                serializeImpl(file, ((ShapeGroup) shape).children(), shape.getID());
            } else if (shape instanceof Shape) {
                var context = ((Shape) shape).getContext();
                String content = shape.getType().toString() + "," + // 0
                        shape.getID() + "," +                       // 1
                        shape.getPosition().x + "," +               // 2
                        shape.getPosition().y + "," +               // 3
                        shape.getSize().getWidth() + "," +          // 4
                        shape.getSize().getHeight() + "," +         // 5
                        shape.isSelected() + "," +                  // 6
                        groupUuid + "," +                           // 7
                        context.getFillColor().getRGB() + "," +     // 8
                        context.getOutlineColor().getRGB() + "," +  // 9
                        context.getStroke() + '\n';                 // 10

                FileManager.write(file, content);
            }
        }
    }
}
