package ui;

import java.awt.*;

public class SelectionFrame extends Component {

    public void paint(Graphics g) {
        if (!isVisible()) {
            return;
        }

        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.ORANGE);
        graphics.drawRect(0, 0, 100, 100);
    }
}
