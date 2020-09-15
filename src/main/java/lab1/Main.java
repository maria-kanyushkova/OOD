package lab1;

import common.FileManager;
import lab1.canvas.Canvas;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes";

    public static void main(String[] args) {
        try {
            FigureDTO figureDTO = parseArgs(args);
            Controller controller = new Controller();
            EventLoop eventLoop = new EventLoop(controller);
            if (figureDTO.getInputPath().isEmpty()) {
                eventLoop.run();
            } else {
                File inputFile = FileManager.getFileByPath(figureDTO.getInputPath());
                eventLoop.run(inputFile);
            }

            writeShapesInfo(controller.getShapeInfo());

            JFrame frame = createUIFrame();

            Canvas canvas = new Canvas();
            frame.add(canvas.getGraphics());
            controller.draw(canvas);

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

    private static JFrame createUIFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(FRAME_TITLE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        return frame;
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
