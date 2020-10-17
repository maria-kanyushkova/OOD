package display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class DisplayImpl implements IDisplay, Runnable {
    private Canvas canvas;

    private final String title;
    private final int width, height;

    private Thread thread;

    private boolean running = false;

    public DisplayImpl(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        init();
    }

    @Override
    public void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        //Clear Screen
        graphics.clearRect(0, 0, width, height);
        //Draw Here!

        // TODO: need implement

        //End Drawing!
        bufferStrategy.show();
        graphics.dispose();
    }

    @Override
    public void run() {
        while(running) {
            runOnTick();
            render();
        }

        stop();
    }

    private synchronized void start(){
        if(running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setLocationByPlatform(true);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    private void runOnTick() {}
}
