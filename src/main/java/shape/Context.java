package shape;

import java.awt.*;

public class Context {
    private Color color;
    private float stroke;

    public Context(Color color) {
        setColor(color);
    }

    public Context(float stroke) {
        setStroke(stroke);
    }

    public Context(Color color, float stroke) {
        setColor(color);
        setStroke(stroke);
    }

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

    public void update(Context context) {
        if (context.color != null && !color.equals(context.color)) {
            color = context.color;
        }
        if (context.stroke != 0 && stroke != context.stroke) {
            stroke = context.stroke;
        }
    }
}
