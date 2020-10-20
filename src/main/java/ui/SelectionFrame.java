package ui;

import java.awt.*;

public class SelectionFrame extends Component {

    public SelectionFrame() {
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
    }
}
