package ui;

import application.Editor;

import java.awt.*;
import java.awt.event.*;

public class EditorCanvas extends Canvas {
    private final Editor editor;

    private SelectionFrame frame = new SelectionFrame();

    public EditorCanvas(Editor editor) {
        super();

        this.editor = editor;

        setBackground(Color.WHITE);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int mods = e.getModifiersEx();
                var isControl = (mods & InputEvent.CTRL_DOWN_MASK) != 0;
                var keyCode = e.getKeyCode();

                if (isControl && keyCode == KeyEvent.VK_G) {
                    editor.group();
                }
                else if (isControl && keyCode == KeyEvent.VK_U) {
                    editor.ungroup();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mods = e.getModifiersEx();
                var isShift = (mods & InputEvent.SHIFT_DOWN_MASK) != 0;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    editor.select(new math.Point(e.getX(), e.getY()), isShift);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics = (Graphics2D) g;

        System.out.println(editor.getDrawableItems().toString());
        for (IDrawable item : editor.getDrawableItems()) {
            item.draw(graphics);
        }

        frame.paint(graphics);
    }
}
