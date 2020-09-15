package lab1;

import lab1.common.Point;
import lab1.common.shape.Rectangle;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    public final double DELTA = 5e-5;
    private Point leftTop = new Point(200, 200);
    private Point rightBottom = new Point(350, 300);
    private Color color = new Color(0, 0, 0);
    private Color fill = new Color(29, 141, 215);
    private Rectangle rectangle = new Rectangle(leftTop, rightBottom, color, fill);

    @Test
    public void getArea() {
        int expected = 15000;
        assertEquals(rectangle.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        int expected = 500;
        assertEquals(rectangle.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Прямоугольник:\n" +
                "Площадь фигуры: 15000\n" +
                "Периметр фигуры: 500\n" +
                "Цвет обводки: #000000\n" +
                "Цвет заливки: #1d8dd7\n" +
                "Левая верхняя точка прямоугольника: x:200 y:200\n" +
                "Правая нижняя точка прямоугольника: x:350 y:300\n" +
                "Ширина прямоугольника: 150\n" +
                "Высота прямоугольника: 100\n";
        assertEquals(rectangle.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        rectangle.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<polygonFill points='point0:`x:200 y:200` point1:`x:350 y:200` point2:`x:350 y:300` point3:`x:200 y:300` ' fill='#1d8dd7' />");
        expected.add("<line from='x:200 y:200' to='x:350 y:200' color='#000000' />");
        expected.add("<line from='x:350 y:200' to='x:350 y:300' color='#000000' />");
        expected.add("<line from='x:350 y:300' to='x:200 y:300' color='#000000' />");
        expected.add("<line from='x:200 y:300' to='x:200 y:200' color='#000000' />");
        assertEquals(canvas.getOut(), expected);
    }
}
