package ui;

import application.command.AddShapeCommand;
import application.command.ChangeShapeContextCommand;
import common.history.History;
import common.history.ICommand;
import shape.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class ApplicationWindow extends JFrame {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    private final EditorCanvas canvas;
    private final History history = new History();

    public ApplicationWindow() {
        setTitle("Shapes");
        setLocationByPlatform(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        createMenuBar();

        canvas = new EditorCanvas(history);
        canvas.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        add(canvas);
        pack();

        start();
    }

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while ( true ) {
                    //sleep for moment
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        break;
                    }

                    render();
                }
            }
        }).start();
    }

    private void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        //Clear Screen
        graphics.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        //Draw Here!

        canvas.paint(graphics);

        //End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }

    private void createMenuBar() {
        // tools
        var tools = new JMenu("Tools");

        createMenuItem(tools, new JMenuItem("Rectangle"), (event) -> executeCommand(new AddShapeCommand(shape.Type.RECTANGLE)), "Create rectangle");
        createMenuItem(tools, new JMenuItem("Triangle"), (event) -> executeCommand(new AddShapeCommand(shape.Type.TRIANGLE)), "Create triangle");
        createMenuItem(tools, new JMenuItem("Ellipse"), (event) -> executeCommand(new AddShapeCommand(shape.Type.ELLIPSE)), "Create ellipse");

        // edit
        var colors = new JMenu("Colors");
        createMenuItem(colors, new JMenuItem("Black"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getFillContext(Color.BLACK))), "Fill black");
        createMenuItem(colors, new JMenuItem("Orange"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getFillContext(Color.ORANGE))), "Fill orange");
        createMenuItem(colors, new JMenuItem("Yellow"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getFillContext(Color.YELLOW))), "Fill yellow");
        createMenuItem(colors, new JMenuItem("White"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getFillContext(Color.WHITE))), "Fill white");

        var strokes = new JMenu("Strokes");
        createMenuItem(strokes, new JMenuItem("1 depth"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getStrokeContext(1.f))), "Change stroke on 1");
        createMenuItem(strokes, new JMenuItem("3 depth"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getStrokeContext(3.f))), "Change stroke on 3");
        strokes.add(new JSeparator());
        createMenuItem(strokes, new JMenuItem("Black"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getOutlineColorContext(Color.BLACK))), "Stroke is black");
        createMenuItem(strokes, new JMenuItem("Gray"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getOutlineColorContext(Color.GRAY))), "Stroke is gray");
        createMenuItem(strokes, new JMenuItem("White"), (event) -> executeCommand(new ChangeShapeContextCommand(Context.getOutlineColorContext(Color.WHITE))), "Stroke is white");

        var edit = new JMenu("Edit");

        edit.add(colors);
        edit.add(strokes);

        // menu bar
        var menuBar = new JMenuBar();

        menuBar.add(tools);
        menuBar.add(edit);

        setJMenuBar(menuBar);
    }

    private void createMenuItem(JMenu group, JMenuItem item, ActionListener listener, String tooltip) {
        item.addActionListener(listener);
        item.setToolTipText(tooltip);
        group.add(item);
    }

    private void executeCommand(ICommand command) {
        history.push(command);
    }
}
