package lab1;

import display.Display;

import lab1.painter.Painter;

public class Application implements Runnable {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes";

    final private ShapeList list;

    private Display display;
    private Thread thread;

    final private Painter painter = new Painter();

    private boolean running = false;

    public Application(ShapeList list) {
       this.list = list;
    }

    @Override
    public void run() {
        init();

        while(running) {
            runOnTick();
            display.render(painter.getRenderable());
        }

        stop();
    }

    public synchronized void start(){
        if(running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
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

    private void runOnTick() {
        list.getShapes().forEach(figure -> figure.draw(painter));
    }

    private void init() {
        display = new Display(FRAME_TITLE, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
