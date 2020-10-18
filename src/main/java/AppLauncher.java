import ui.ApplicationWindow;

import javax.swing.UIManager;

public class AppLauncher {
    public static void main(String[] args) {
        //Set the look and feel to the OS look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplicationWindow window = new ApplicationWindow();
        window.setDefaultCloseOperation(ApplicationWindow.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
