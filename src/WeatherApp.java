import javax.swing.*;
import java.awt.*;

public class WeatherApp extends JFrame{
    public WeatherApp(){
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addGuiComponents();
    }
    private void addGuiComponents(){
        JTextField searchTextField = new JTextField();

        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);
    }

}