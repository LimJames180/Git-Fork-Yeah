package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginPresenter;
import data_access.UserDataAccess;
import data_access.MongoUserDataAccessImpl;
import interface_adapter.login.SignupController;
import use_case.note.LoginInputBoundary;
import use_case.note.LoginOutputBoundary;
import use_case.note.LoginInteractor;
import use_case.note.SignupInputBoundary;

public class LoginPageView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton guestButton; // New guest button
    private LoginController controller;
    private LoginViewModel viewModel;
    private SignupController signupController;

    public LoginPageView(LoginController controller, LoginViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.signupController = signupController;

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

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);

        // Signup Button
        signupButton = new JButton("Signup");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(signupButton, gbc);

        guestButton = new JButton("Continue as Guest"); // Initialize the guest button
        gbc.gridx = 0;
        gbc.gridy = 3; // Place it below the signup button
        gbc.gridwidth = 2; // Span across both columns
        add(guestButton, gbc);

        setVisible(true);
    }

    private void setupListeners() {
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
//            controller.handleLogin(username, password);
            new LoggedInPageView("randomusername");
        });

        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
//            signupController.handleSignup(username, password);
            new LoggedInPageView("randomusername");
        });

        guestButton.addActionListener(e -> {
            // Logic for continuing as a guest
//            JOptionPane.showMessageDialog(this, "Continuing as guest...", "Guest Access", JOptionPane.INFORMATION_MESSAGE);
            // You can add additional logic here to navigate to the guest view
            new IngredientSearchView();
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

    public static void main(String[] args) {
        UserDataAccess userDataAccess = new MongoUserDataAccessImpl();
        LoginOutputBoundary outputBoundary = new LoginPresenter(new LoginViewModel());
        LoginInputBoundary interactor = new LoginInteractor(userDataAccess, outputBoundary);
        LoginController controller = new LoginController(interactor);
        LoginViewModel viewModel = new LoginViewModel();

//        SignupInputBoundary interactor2 = new;
//        SignupController signupController1 = new SignupController(interactor2);

        SwingUtilities.invokeLater(() -> new LoginPageView(controller, viewModel));

    }
}
