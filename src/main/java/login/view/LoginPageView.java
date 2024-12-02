package login.view;

import javax.swing.*;
import java.awt.*;

import login.app.SessionService;
import login.interface_adapter.LoginController;
import login.interface_adapter.LoginViewModel;
import login.interface_adapter.SignupController;
import login.interface_adapter.SignupViewModel;
import misc_view.LoggedInPageView;

import java.beans.PropertyChangeListener;

/**
 * Represents the login page view of the application.
 * This class handles the user interface for logging in and signing up.
 */
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

    /**
     * Constructs a LoginPageView with the specified controllers and session service.
     *
     * @param controller the LoginController to handle login actions
     * @param viewModel the LoginViewModel to manage the view's state
     * @param signupController the SignupController to handle signup actions
     * @param signupViewModel the SignupViewModel to manage the signup view's state
     * @param currentSession the current session service
     */
    public LoginPageView(LoginController controller, LoginViewModel viewModel,
                         SignupController signupController, SignupViewModel signupViewModel,
                         SessionService currentSession) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.currentSession = currentSession;
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        setupUI();
        setupListeners();
        setupViewModel();
    }

    /**
     * Constructs a LoginPageView with the specified controllers and
     * a new session service (specifically for Signup use).
     *
     * @param controller the LoginController to handle login actions
     * @param viewModel the LoginViewModel to manage the view's state
     */
    public LoginPageView(LoginController controller, LoginViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.currentSession = new SessionService();

        setupUI();
        setupListeners();
        setupViewModel();
    }

    /**
     * Sets up the user interface components of the login page.
     */
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

    /**
     * Sets up the action listeners for the login and signup buttons.
     */
    private void setupListeners() {
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            currentSession.setUsername(username);

            controller.handleLogin(username, password);
        });

        signupButton.addActionListener(e -> {
            SignupPageView signupPageView = new SignupPageView(signupController, signupViewModel, controller, viewModel);
            signupPageView.setVisible(true);
            dispose();
        });
    }

    /**
     * Sets up the view model to listen for property changes and update the UI accordingly.
     */
    private void setupViewModel() {
        for (PropertyChangeListener listener : viewModel.getPropertyChangeListeners()) {
            viewModel.removePropertyChangeListener(listener);
        }

        viewModel.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getMessage(),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoggedInPageView(viewModel.getMessage(), viewModel.
                        getSavedRecipes(currentSession.getUsername()), currentSession);
            } else if ("error".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, viewModel.getError(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
