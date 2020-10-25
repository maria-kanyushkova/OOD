package ui;

import application.Editor;
import controller.InteractionController;

import java.awt.*;

public class EditorCanvas extends Canvas {
    private final Editor editor;

    public EditorCanvas(Editor editor) {
        super();

        this.editor = editor;
        var controller = new InteractionController(editor, this);

        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics = (Graphics2D) g;

        for (IDrawable item : editor.getDrawableItems()) {
            item.draw(graphics);
        }
    }
}
