package application;


public final class Application {
    private static volatile Application instance;

    private Application() {

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
}
