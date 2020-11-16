package controller;

import application.Editor;
import application.command.GroupShapeCommand;
import application.command.MoveShapeCommand;
import application.command.UngroupShapeCommand;
import common.history.IHistory;
import shape.IShape;

import java.awt.*;
import java.awt.event.*;

public class InteractionController {
    private final Component component;
    private final Editor editor;
    private final IHistory history;

    private Point initialShapePosition = null;
    private Point initialMousePositionOnScreen = null;
    private IShape draggableShape = null;

    public InteractionController(Component component, IHistory history) {
        this.editor = Editor.getInstance();
        this.component = component;
        this.history = history;

        this.component.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) { onKeyPressed(e); }
        });

        this.component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { onMousePressed(e); }

            public void mouseReleased(MouseEvent e) { onMouseReleased(e); }
        });

        this.component.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) { onMouseDragged(e); }

            public void mouseMoved(MouseEvent e) {}
        });
    }

    private void onKeyPressed(KeyEvent event) {
        int mods = event.getModifiersEx();
        var isControl = (mods & InputEvent.CTRL_DOWN_MASK) != 0;
        var isShift = (mods & InputEvent.SHIFT_DOWN_MASK) != 0;
        var keyCode = event.getKeyCode();

        if (isControl && keyCode == KeyEvent.VK_G) {
            var shapes = Editor.getInstance().getSelectedShapes();
            if (!shapes.isEmpty()) {
                history.push(new GroupShapeCommand(shapes));
            }
        } else if (isControl && keyCode == KeyEvent.VK_U) {
            var shapes = Editor.getInstance().getSelectedShapes();
            if (!shapes.isEmpty()) {
                history.push(new UngroupShapeCommand(shapes));
            }

        } else if (isControl && keyCode == KeyEvent.VK_Z) {
            if (isShift) {
                history.redo();
                return;
            }
            history.undo();
        }
    }

    private void onMousePressed(MouseEvent event) {
        int mods = event.getModifiersEx();
        var isShift = (mods & InputEvent.SHIFT_DOWN_MASK) != 0;
        if (event.getButton() == MouseEvent.BUTTON1) {
            var shape = editor.getInterceptingShape(new Point(event.getX(), event.getY()));
            editor.select(shape, isShift);

            if (shape != null) {
                draggableShape = shape;
                initialShapePosition = shape.getPosition();
                initialMousePositionOnScreen = event.getLocationOnScreen();
            }
        }
    }

    private void onMouseReleased(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
            component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            if (draggableShape != null) {
                history.push(new MoveShapeCommand(draggableShape, initialShapePosition, draggableShape.getPosition()));
                draggableShape = null;
            }

        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (draggableShape == null) {
            return;
        }

        Point mouseLocation = event.getLocationOnScreen();

        int deltaX = mouseLocation.x - initialMousePositionOnScreen.x;
        int deltaY = mouseLocation.y - initialMousePositionOnScreen.y;

        component.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

        var newPosition = new Point(initialShapePosition);
        newPosition.translate(deltaX, deltaY);
        draggableShape.setPosition(newPosition);
    }
}
