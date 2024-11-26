package login.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import login.data_access.UserDataAccess;
import login.data_access.MongoUserDataAccessImpl;
import login.entities.Recipe;

public class SampleRecipeView extends JFrame {
    private JButton saveRecipeButton;
    private JButton getRecipesButton;
    private JTextArea recipesArea;
    private UserDataAccess userDataAccess;

    public SampleRecipeView() {
        userDataAccess = new MongoUserDataAccessImpl(); // Initialize your data access

        setTitle("Sample Recipe View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Save Recipe Button
        saveRecipeButton = new JButton("Save Recipe");
        saveRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecipe("324694", "Sample Recipe Title"); // Sample recipe ID and title
            }
        });

        // Get Recipes Button
        getRecipesButton = new JButton("Get Recipes");
        getRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSavedRecipes();
            }
        });

        // Text Area to display saved recipes
        recipesArea = new JTextArea();
        recipesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipesArea);

        // Add components to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveRecipeButton);
        buttonPanel.add(getRecipesButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void saveRecipe(String recipeId, String title) {
        // Create a new Recipe object
        Recipe recipe = new Recipe(recipeId, title);
        // Here you would typically get the current user's username
        String currentUsername = "SampleUser"; // Replace with actual username logic

        // Save the recipe to the user's account
        userDataAccess.saveRecipeForUser(currentUsername, recipe);
        JOptionPane.showMessageDialog(this, "Recipe saved successfully!");
    }

    private void getSavedRecipes() {
        // Here you would typically get the current user's username
        String currentUsername = "SampleUser"; // Replace with actual username logic

        // Retrieve saved recipes for the user
        StringBuilder savedRecipes = new StringBuilder();
        userDataAccess.getUserRecipes(currentUsername).forEach(recipe -> {
            savedRecipes.append("Recipe ID: ").append(recipe.getRecipeId()).append(" - Title: ").append(recipe.getTitle()).append("\n");
        });

        // Display saved recipes
        recipesArea.setText(savedRecipes.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SampleRecipeView::new);
    }
}