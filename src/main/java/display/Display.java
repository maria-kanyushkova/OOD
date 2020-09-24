package display;

import lab1.painter.IRenderable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        init();
    }

    public void init() {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setLocationByPlatform(true);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    public void render(IRenderable renderable) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        //Clear Screen
        graphics.clearRect(0, 0, width, height);
        //Draw Here!

        renderable.render(graphics);

        //End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }
}
