package shape;

import java.awt.*;

public class Context {
    private Color color = Color.WHITE;
    private float stroke = 1.f;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
    }

    public float getStroke() {
        return stroke;
    }
}
