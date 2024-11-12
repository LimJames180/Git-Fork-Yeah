package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageView extends JFrame {
    // UI Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton guestButton;

    public LoginPageView() {
        // Setting up the frame
        setTitle("Recipe Finder - Login");
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
        gbc.gridy = 0;
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        // Buttons Panel (for Login and Signup)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Continue as Guest Button (placed at the bottom)
        guestButton = new JButton("Continue as Guest");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(guestButton, gbc);

        // Event Listeners (Placeholder Actions)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check for missing fields
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPageView.this, "Error: Please fill in both username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Placeholder for database check (assess whether username and password match)
                boolean isValidUser = false; // Replace with actual database check
                if (isValidUser) {
                    JOptionPane.showMessageDialog(LoginPageView.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the login window
                    new LoggedInPageView(username); // Navigate to LoggedInPageView
                } else {
                    JOptionPane.showMessageDialog(LoginPageView.this, "Error: Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check for missing fields
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPageView.this, "Error: Please fill in both username and password.", "Signup Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Placeholder for database check (assess whether username already exists)
                boolean userExists = false; // Replace with actual database check
                if (userExists) {
                    JOptionPane.showMessageDialog(LoginPageView.this, "Error: Username already exists. Please choose another.", "Signup Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Placeholder for storing new user in database
                    // Replace with actual database storage logic
                    JOptionPane.showMessageDialog(LoginPageView.this, "Signup successful! Welcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the signup window
                    new LoggedInPageView(username); // Navigate to LoggedInPageView
                }
            }
        });

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to proceed as guest
                // JOptionPane.showMessageDialog(LoginPageView.this, "Continuing as Guest...");
                new SearchRecipeView();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPageView::new);
    }
}
