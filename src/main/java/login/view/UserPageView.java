package login.view;

import login.data_access.MongoUserDataAccessImpl;
import login.data_access.UserDataAccess;
import login.entities.Recipe;
import view.IngredientSearchView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserPageView extends JFrame{
    // UI Components
    private JLabel welcomeLabel;
    private JList<String> savedRecipesList;
    private JButton exploreRecipesButton;
    private DefaultListModel<String> savedRecipesModel;
    private UserDataAccess userDataAccess;
    private JTextArea recipesArea;


    public UserPageView(String username) {
        userDataAccess = new MongoUserDataAccessImpl(); // Initialize your data access

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
//        savedRecipesModel = new DefaultListModel<>();
//        // Placeholder for saved recipes fetched from Spoonacular API (using example data)
//        savedRecipesModel.addElement("Tomato Pasta - Recipe ID: 12345");
//        savedRecipesModel.addElement("Another Saved Recipe - Recipe ID: 67890"); // Example placeholder
        recipesArea = new JTextArea();
        getSavedRecipes(username);
        recipesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipesArea);
        add(scrollPane, BorderLayout.CENTER);

//
//        savedRecipesList = new JList<>(savedRecipesModel);
//        savedRecipesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        JScrollPane recipeScrollPane = new JScrollPane(savedRecipesList);
//        recipeScrollPane.setBorder(BorderFactory.createTitledBorder("Saved Recipes"));
//        add(recipeScrollPane, BorderLayout.CENTER);

        // Explore Recipes Button
        exploreRecipesButton = new JButton("Explore Recipes");
        exploreRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to navigate to recipe exploration view
//                JOptionPane.showMessageDialog(LoggedInPageView.this, "Navigating to Explore Recipes...");
                new IngredientSearchView(null);
            }
        });
        add(exploreRecipesButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void getSavedRecipes(String username) {
        // Here you would typically get the current user's username
        String currentUsername = "nov22"; // Replace with actual username logic

        // Retrieve saved recipes for the user
        StringBuilder savedRecipes = new StringBuilder();
        userDataAccess.getUserRecipes(currentUsername).forEach(recipe -> {
            savedRecipes.append("Recipe ID: ").append(recipe.getRecipeId()).append(" - Title: ").append(recipe.getTitle()).append("\n");
        });

        // Display saved recipes
        if (savedRecipes.toString() == null) {
            recipesArea.setText("");
        } else {
            recipesArea.setText(savedRecipes.toString());
        }
    }


}
