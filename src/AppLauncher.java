import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeatherAppGUI().setVisible(true);
            System.out.println("Application launched successfully.");
        });
    }
}
