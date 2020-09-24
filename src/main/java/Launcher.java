import common.FileManager;
import lab1.Application;
import lab1.EventLoop;
import lab1.ShapeList;
import lab1.FigureDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) {
        try {
            FigureDTO figureDTO = parseArgs(args);
            ShapeList list = new ShapeList();
            EventLoop eventLoop = new EventLoop(list);
            if (figureDTO.getInputPath().isEmpty()) {
                eventLoop.run();
            } else {
                File inputFile = FileManager.getFileByPath(figureDTO.getInputPath());
                eventLoop.run(inputFile);
            }

            writeShapesInfo(list.getShapeInfo());

            var application = new Application(list);
            application.start();

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static FigureDTO parseArgs(String[] args) throws IllegalArgumentException {
        String inputPath = "";
        if (args.length != 0) {
            inputPath = args[0];
        }
        return new FigureDTO(inputPath);
    }

    private static void writeShapesInfo(ArrayList<String> shapes) throws IOException {
        File outputFile = FileManager.create("src/main/resources/lab1/output.txt");
        try (
                FileWriter writer = new FileWriter(outputFile);
        ) {
            for (String shape : shapes) {
                writer.write(shape + "\n");
            }
        }
    }
}
