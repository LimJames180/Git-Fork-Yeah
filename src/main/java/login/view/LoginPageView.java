package login.view;


import javax.swing.*;
import java.awt.*;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginViewModel;
import login.interface_adapter.SignupController;
import login.interface_adapter.SignupPresenter;
import login.interface_adapter.*;
import login.use_case.SignupInteractor;
import login.data_access.MongoUserDataAccessImpl;
import view.LoggedInPageView;



public class LoginPageView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private LoginController controller;
    private LoginViewModel viewModel;

    public LoginPageView(LoginController controller, LoginViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setupUI();
        setupListeners();
        setupViewModel();
    }

    private void setupUI() {
        setTitle("Recipe Finder - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
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

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);

        signupButton = new JButton("Signup");
        gbc.gridx = 1;
        add(signupButton, gbc);

        setVisible(true);
    }

    private void setupListeners() {
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate inputs
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please input both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Handle login
            controller.handleLogin(username, password);
        });

        signupButton.addActionListener(e -> {
            // Navigate to signup page
            SignupPageView signupPageView = new SignupPageView(new SignupController(new SignupInteractor(new MongoUserDataAccessImpl(), new SignupPresenter(new SignupViewModel()))), new SignupViewModel());
            signupPageView.setVisible(true);
            dispose(); // Close the login page
        });
    }

    private void setupViewModel() {
        viewModel.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoggedInPageView(viewModel.getMessage());
            } else if ("error".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}