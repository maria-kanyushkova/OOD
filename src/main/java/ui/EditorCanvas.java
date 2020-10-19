package ui;

import application.Editor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class EditorCanvas extends Canvas {
    private final Editor model;

    public EditorCanvas(Editor model) {
        super();

        this.model = model;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int mods = e.getModifiersEx();
                var isControl = (mods & InputEvent.CTRL_DOWN_MASK) != 0;

                if (isControl && e.getKeyCode() == KeyEvent.VK_G) {
                    System.out.println("Pressed ctrl+G");
                }
                else if (isControl && e.getKeyCode() == KeyEvent.VK_U) {
                    System.out.println("Pressed ctrl+U");
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mods = e.getModifiersEx();
                var isShift = (mods & InputEvent.SHIFT_DOWN_MASK) != 0;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Clicked Shift+LeftClick multi" + isShift);
                    System.out.println("Position: " + e.getX() + ' ' + e.getY());
                }
            }
        });
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
