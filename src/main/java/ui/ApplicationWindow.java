package ui;

import application.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class ApplicationWindow extends JFrame {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    private final EditorCanvas canvas;

    public ApplicationWindow() {
        setTitle("Shapes");
        setLocationByPlatform(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        var editor = new Editor();

        createMenuBar(editor);

        canvas = new EditorCanvas(editor);
        canvas.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        add(canvas);
        pack();

        start();
    }

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( true ) {
                    //sleep for moment
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        break;
                    }

                    render();
                }
            }
        }).start();
    }

    private void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        //Clear Screen
        graphics.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        //Draw Here!

        canvas.paint(graphics);

        //End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }

    private void createMenuBar(Editor editor) {
        // tools
        var tools = new JMenu("Tools");

        createMenuItem(tools, new JMenuItem("Rectangle"), (event) -> editor.createRectangle(), "Create rectangle");
        createMenuItem(tools, new JMenuItem("Triangle"), (event) -> editor.createTriangle(), "Create triangle");
        createMenuItem(tools, new JMenuItem("Ellipse"), (event) -> editor.createEllipse(), "Create ellipse");

        // edit
        var colors = new JMenu("Colors");
        createMenuItem(colors, new JMenuItem("White"), (event) -> {}, "Fill white");
        createMenuItem(colors, new JMenuItem("Black"), (event) -> {}, "Fill black");
        createMenuItem(colors, new JMenuItem("Green"), (event) -> {}, "Fill green");
        createMenuItem(colors, new JMenuItem("Orange"), (event) -> {}, "Fill orange");

        var strokes = new JMenu("Strokes");
        createMenuItem(strokes, new JMenuItem("1 depth"), (event) -> {}, "Change stroke on 1");
        createMenuItem(strokes, new JMenuItem("2 depth"), (event) -> {}, "Change stroke on 21");
        createMenuItem(strokes, new JMenuItem("4 depth"), (event) -> {}, "Change stroke on 5");

        var edit = new JMenu("Edit");

        edit.add(colors);
        edit.add(strokes);

        // menu bar
        var menuBar = new JMenuBar();

        menuBar.add(tools);
        menuBar.add(edit);

        setJMenuBar(menuBar);
    }

    private void createMenuItem(JMenu group, JMenuItem item, ActionListener listener, String tooltip) {
        item.addActionListener(listener);
        item.setToolTipText(tooltip);
        group.add(item);
    }
}
