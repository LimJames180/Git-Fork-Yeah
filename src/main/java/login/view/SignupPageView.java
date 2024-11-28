package login.view;

import javax.swing.*;
import java.awt.*;

import login.data_access.MongoUserDataAccessImpl;
import login.data_access.UserDataAccess;
import login.interface_adapter.*;
import login.use_case.LoginInteractor;

public class SignupPageView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signupButton;
    private SignupController controller;
    private SignupViewModel viewModel;
    private LoginController loginController;
    private LoginViewModel loginViewModel;

    public SignupPageView(SignupController controller, SignupViewModel viewModel, LoginController loginController, LoginViewModel loginViewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;

        setupUI();
        setupListeners();
        setupViewModel();
    }

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


    private void setupListeners() {
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please input both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ensure this method updates the viewModel with a message or error
            controller.handleSignup(username, password);

        });
    }

    private void setupViewModel() {
        viewModel.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                // Check if this block is being executed
                JOptionPane.showMessageDialog(this, viewModel.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                LoginPageView loginPageView = new LoginPageView(loginController, loginViewModel);
                loginPageView.setVisible(true);
                // Optionally navigate to login or another view
            } else if ("error".equals(evt.getPropertyName())) {
                // Check if this block is being executed
                JOptionPane.showMessageDialog(this, viewModel.getError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
