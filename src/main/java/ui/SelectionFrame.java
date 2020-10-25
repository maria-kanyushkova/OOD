package ui;

import javax.swing.*;
import java.awt.*;

public class SelectionFrame extends JComponent {
    private static final int DIST = 8;

    private final int[] locations = {
        SwingConstants.NORTH_WEST,
        SwingConstants.NORTH_EAST,
        SwingConstants.SOUTH_WEST,
        SwingConstants.SOUTH_EAST
    };

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());

        for (int location : locations) {
            var rect = getRectangle(getX() - DIST / 2, getY() - DIST / 2, getWidth(), getHeight(), location);

            if (rect != null) {
                g.setColor(Color.WHITE);
                g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
                g.setColor(Color.ORANGE);
                g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
            }
        }
    }

    private Rectangle getRectangle(int x, int y, int w, int h, int location) {
        return switch (location) {
            case SwingConstants.NORTH_WEST -> new Rectangle(x, y, DIST, DIST);
            case SwingConstants.NORTH_EAST -> new Rectangle(x + w, y, DIST, DIST);
            case SwingConstants.SOUTH_WEST -> new Rectangle(x, y + h, DIST, DIST);
            case SwingConstants.SOUTH_EAST -> new Rectangle(x + w, y + h, DIST, DIST);
            default -> new Rectangle();
        };
    }
}
