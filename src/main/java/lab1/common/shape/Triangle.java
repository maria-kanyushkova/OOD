package lab1.common.shape;

import lab1.canvas.IPainter;
import lab1.common.IPhysicShape;
import lab1.common.Point;

public class Triangle implements IPhysicShape {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Point getVertex1() {
        return vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public Point getVertex3() {
        return vertex3;
    }

    @Override
    public int getArea() {
        double width12 = getLineWidth(vertex1, vertex2);
        double width23 = getLineWidth(vertex2, vertex3);
        double width31 = getLineWidth(vertex3, vertex1);
        double p = (width12 + width23 + width31) / 2;
        return (int) Math.sqrt(p * (p - width12) * (p - width23) * (p - width31));
    }

    @Override
    public int getPerimeter() {
        return (int) (getLineWidth(vertex1, vertex2) + getLineWidth(vertex2, vertex3) + getLineWidth(vertex3, vertex1));
    }

    private double getLineWidth(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    @Override
    public void draw(IPainter painter) {
        painter.draw(painter.createLine(vertex1, vertex2));
        painter.draw(painter.createLine(vertex2, vertex3));
        painter.draw(painter.createLine(vertex3, vertex1));
    }
}
