import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A class for the login screen.
 */
public class LoginScreenSwing extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private int screenWidth = 400;
    private int screenHeight = 300;

    public LoginScreenSwing() {
        setTitle("Fork Yeah!");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        loginButton = new JButton("Log In");
        inputPanel.add(new JLabel("Ingredient:"));
        inputPanel.add(usernameField);
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);

        // Button action
        loginButton.addActionListener(new LoginButtonListener() {
            public void loginButtonClicked(ActionEvent e) {
                // first pass relevant info to next screen
                // then open next screen and close this one
                dispose();
            }
        });
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
