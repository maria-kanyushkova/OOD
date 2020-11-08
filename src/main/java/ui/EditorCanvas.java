package ui;

import application.Editor;
import controller.InteractionController;

import java.awt.*;

public class EditorCanvas extends Canvas {
    public EditorCanvas() {
        super();

        var controller = new InteractionController(this);

        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics = (Graphics2D) g;

        for (IDrawable item : Editor.getInstance().getDrawableItems()) {
            item.draw(graphics);
        }
    }
}
