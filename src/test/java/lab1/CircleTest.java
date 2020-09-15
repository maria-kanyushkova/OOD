package lab1;

import lab1.common.Point;
import lab1.common.shape.Circle;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CircleTest {
    public final double DELTA = 5e-5;
    private Point center = new Point(100, 100);
    private int radius = 25;
    private Color color = new Color(0, 0, 0);
    private Color fill = new Color(29, 141, 215);
    private Circle circle = new Circle(center, radius, color, fill);

    @Test
    public void getArea() {
        int expected = 1962;
        assertEquals(circle.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        int expected = 157;
        assertEquals(circle.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Круг:\n" +
                "Площадь фигуры: 1962\n" +
                "Периметр фигуры: 157\n" +
                "Цвет обводки: #000000\n" +
                "Цвет заливки: #1d8dd7\n" +
                "Точка центра окружности: " + center.toString() + "\n" +
                "Радиус окружности: 25\n";
        assertEquals(circle.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        circle.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<circleFill center='x:100 y:100' radius='25.0' fill='#1d8dd7' />");
        expected.add("<circle center='x:100 y:100' radius='25.0' color='#000000' />");
        assertEquals(canvas.getOut(), expected);
    }
}
