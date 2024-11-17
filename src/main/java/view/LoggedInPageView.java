package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInPageView extends JFrame {
    // UI Components
    private JLabel welcomeLabel;
    private JList<String> savedRecipesList;
    private JButton exploreRecipesButton;
    private DefaultListModel<String> savedRecipesModel;

    public LoggedInPageView(String username) {
        // Setting up the frame
        setTitle("Recipe Finder - Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Welcome Message
        welcomeLabel = new JLabel("Welcome Back, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        // Saved Recipes List
        savedRecipesModel = new DefaultListModel<>();
        // Placeholder for saved recipes fetched from Spoonacular API (using example data)
        savedRecipesModel.addElement("Tomato Pasta - Recipe ID: 12345");
        savedRecipesModel.addElement("Another Saved Recipe - Recipe ID: 67890"); // Example placeholder

        savedRecipesList = new JList<>(savedRecipesModel);
        savedRecipesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane recipeScrollPane = new JScrollPane(savedRecipesList);
        recipeScrollPane.setBorder(BorderFactory.createTitledBorder("Saved Recipes"));
        add(recipeScrollPane, BorderLayout.CENTER);

        // Explore Recipes Button
        exploreRecipesButton = new JButton("Explore Recipes");
        exploreRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to navigate to recipe exploration view
//                JOptionPane.showMessageDialog(LoggedInPageView.this, "Navigating to Explore Recipes...");
                new IngredientSearchView();
            }
        });
        add(exploreRecipesButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Example invocation with a sample username
        SwingUtilities.invokeLater(() -> new LoggedInPageView("SampleUser"));
    }
}

