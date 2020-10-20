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
                        Thread.sleep(100);
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
}
