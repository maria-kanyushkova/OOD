package application;

import display.DisplayImpl;
import display.IDisplay;

public final class Application {
    private static volatile Application instance;

    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes";

    private final IDisplay display;

    private Application() {
        var displayImpl = new DisplayImpl(FRAME_TITLE, FRAME_WIDTH, FRAME_HEIGHT);
        display = displayImpl;
        displayImpl.run();
    }

    public static Application getInstance() {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    instance = new Application();
                }
            }
        }
        return instance;
    }

    public IDisplay getDisplay() {
        return display;
    }
}
