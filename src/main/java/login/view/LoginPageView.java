package login.view;


import javax.swing.*;
import java.awt.*;

import entity.Recipe;
import login.app.SessionService;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginViewModel;
import login.interface_adapter.SignupController;
import login.interface_adapter.SignupPresenter;
import login.interface_adapter.*;
import login.use_case.SignupInteractor;
import login.data_access.MongoUserDataAccessImpl;
import view.LoggedInPageView;
import java.util.List;

public class LoginPageView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private LoginController controller;
    private LoginViewModel viewModel;
    private SessionService currentSession;
    private SignupController signupController;
    private SignupViewModel signupViewModel;

    public LoginPageView(LoginController controller, LoginViewModel viewModel, SignupController signupController, SignupViewModel signupViewModel, SessionService currentSession) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.currentSession = currentSession;
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        setupUI();
        setupListeners();
        setupViewModel();
    }

    public LoginPageView(LoginController controller, LoginViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.currentSession = new SessionService();

        setupUI();
        setupListeners();
        setupViewModel();
    }

    private void setupUI() {
        setTitle("entity.Recipe Finder - Login");
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
            currentSession.setUsername(username);
        });

        signupButton.addActionListener(e -> {
            // Navigate to signup page
            SignupPageView signupPageView = new SignupPageView(signupController, signupViewModel, controller, viewModel);
            signupPageView.setVisible(true);
            dispose(); // Close the login page
        });
    }

    private void setupViewModel() {
        viewModel.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                Recipe test = new Recipe("324694");
                List<Recipe> testy = List.of(test, test);

//                new LoggedInPageView(viewModel.getMessage(), viewModel.getSavedRecipes()); // Pass saved recipes if available
                new LoggedInPageView(viewModel.getMessage(), viewModel.getSavedRecipes(currentSession.getUsername()), currentSession); // Pass saved recipes if available

            } else if ("error".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}