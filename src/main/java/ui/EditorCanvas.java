package ui;

import application.Editor;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EditorCanvas extends Canvas {
    private final Editor model;

    public EditorCanvas(Editor model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        //Clear Screen
        graphics.clearRect(0, 0, getWidth(), getHeight());
        //Draw Here!

        model.draw(graphics);

        //End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }
}
