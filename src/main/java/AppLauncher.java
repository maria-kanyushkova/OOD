import application.Application;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            var application = Application.getInstance();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
