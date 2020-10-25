package ui;

import application.Editor;

import javax.swing.*;
import java.awt.*;
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
        var menuBar = new JMenuBar();
        var tools = new JMenu("Tools");

        var createRectangleItem = new JMenuItem("Rectangle");
        createRectangleItem.setToolTipText("Create rectangle");
        createRectangleItem.addActionListener((event) -> editor.createRectangle());

        var createTriangleItem = new JMenuItem("Triangle");
        createTriangleItem.setToolTipText("Create triangle");
        createTriangleItem.addActionListener((event) -> editor.createTriangle());

        var createEllipseItem = new JMenuItem("Ellipse");
        createEllipseItem.setToolTipText("Create ellipse");
        createEllipseItem.addActionListener((event) -> editor.createEllipse());

        tools.add(createRectangleItem);
        tools.add(createTriangleItem);
        tools.add(createEllipseItem);

        menuBar.add(tools);

        setJMenuBar(menuBar);
    }
}
