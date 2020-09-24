package lab1;

import display.Display;

public class Application implements Runnable {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes";

    private Display display;

    private boolean running = false;
    private Thread thread;

    @Override
    public void run() {
        init();

        while(running) {
            display.render();
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

    private void init() {
        display = new Display(FRAME_TITLE, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
