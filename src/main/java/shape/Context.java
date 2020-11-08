package shape;

import java.awt.*;

public class Context {
    private Color fillColor;
    private Color outlineColor;
    private float stroke;

    public Context() {}

    public Context(Color fillColor, float stroke) {
        setFillColor(fillColor);
        setOutlineColor(Color.BLACK);
        setStroke(stroke);
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
    }

    public float getStroke() {
        return stroke;
    }

    public void update(Context context) {
        if (context.fillColor != null && !fillColor.equals(context.fillColor)) {
            fillColor = context.fillColor;
        }
        if (context.outlineColor != null && !outlineColor.equals(context.outlineColor)) {
            outlineColor = context.outlineColor;
        }
        if (context.stroke != 0 && stroke != context.stroke) {
            stroke = context.stroke;
        }
    }

    static public Context getOutlineColorContext(Color color) {
        var context = new Context();
        context.setOutlineColor(color);

        return context;
    }

    static public Context getStrokeContext(float stroke) {
        var context = new Context();
        context.setStroke(stroke);

        return context;
    }

    static public Context getFillContext(Color color) {
        var context = new Context();
        context.setFillColor(color);

        return context;
    }
}
