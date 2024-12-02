package login.view;

import javax.swing.*;
import java.awt.*;

import login.interface_adapter.SignupController;
import login.interface_adapter.SignupViewModel;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginViewModel;

/**
 * Represents the signup page view in the application.
 * This class handles the user interface for signing up a new user,
 * including input fields for username and password, and a signup button.
 */
public class SignupPageView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signupButton;
    private SignupController controller;
    private SignupViewModel viewModel;
    private LoginController loginController;
    private LoginViewModel loginViewModel;

    /**
     * Constructs a SignupPageView with the specified controllers and view models.
     *
     * @param controller the SignupController to handle signup logic
     * @param viewModel the SignupViewModel to manage the view's state
     * @param loginController the LoginController to handle login logic
     * @param loginViewModel the LoginViewModel to manage the login view's state
     */
    public SignupPageView(SignupController controller, SignupViewModel viewModel,
                          LoginController loginController, LoginViewModel loginViewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;

        setupUI();
        setupListeners();
        setupViewModel();
    }

    /**
     * Sets up the user interface components for the signup page.
     * This includes setting the title, layout, and adding input fields and buttons.
     */
    private void setupUI() {
        setTitle("Recipe Finder - Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        signupButton = new JButton("Signup");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(signupButton, gbc);

        setVisible(true);
    }

    /**
     * Sets up the action listeners for the UI components.
     * Specifically, it handles the signup button click event.
     */
    private void setupListeners() {
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please input both username and password.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.handleSignup(username, password);
        });
    }

    /**
     * Configures the view model to listen for property changes.
     * It handles messages and errors from the view model and displays them to the user.
     */
    private void setupViewModel() {
        viewModel.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getMessage(),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();

                LoginPageView loginPageView = new LoginPageView(loginController, loginViewModel);

                loginPageView.setVisible(true);
            } else if ("error".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getError(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
