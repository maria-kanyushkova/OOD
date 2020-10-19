package ui;

import application.Editor;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    public ApplicationWindow() {
        setTitle("Shapes");
        setLocationByPlatform(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        var editor = new Editor();
        var canvas = new EditorCanvas(editor);
        canvas.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        add(canvas);
        pack();
    }
}
