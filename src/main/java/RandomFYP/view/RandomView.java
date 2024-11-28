package RandomFYP.view;

import entity.Ingredient;
import entity.Recipe;
import interface_adapter.RecipeController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static interface_adapter.RecipeController.Random_recipe;

public class RandomView {

    // Placeholder for the current batch of recipes
    private static List<Recipe> currentRecipes;
    private static int currentRecipeIndex = 0;

    public static void main(String[] args) throws IOException {
        // Load the first batch of recipes
        currentRecipes = Random_recipe();
        if (currentRecipes == null || currentRecipes.isEmpty()) {
            throw new RuntimeException("No recipes found from Random_recipe!");
        }

        // Frame setup
        JFrame frame = new JFrame("Random Recipe Generator");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Random Recipe Generator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Recipe display panel
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(recipePanel, BorderLayout.CENTER);

        // Image label
        JLabel imageLabel = new JLabel("", JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        recipePanel.add(imageLabel, BorderLayout.NORTH);

        // Text areas for title, ingredients, and steps
        JTextArea recipeTitle = new JTextArea();
        recipeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        recipeTitle.setEditable(false);
        recipeTitle.setWrapStyleWord(true);
        recipeTitle.setLineWrap(true);
        recipeTitle.setAlignmentX(JTextArea.CENTER_ALIGNMENT);


        JTextArea recipeIngredients = new JTextArea();
        recipeIngredients.setFont(new Font("Arial", Font.PLAIN, 14));
        recipeIngredients.setEditable(false);
        recipeIngredients.setWrapStyleWord(true);
        recipeIngredients.setLineWrap(true);
        recipeIngredients.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        JTextArea recipeSteps = new JTextArea();
        recipeSteps.setFont(new Font("Arial", Font.PLAIN, 14));
        recipeSteps.setEditable(false);
        recipeSteps.setWrapStyleWord(true);
        recipeSteps.setLineWrap(true);
        recipeSteps.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        // Scrollable panel for ingredients and steps
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.add(recipeTitle);
        textPanel.add(new JScrollPane(recipeIngredients));
        textPanel.add(new JScrollPane(recipeSteps));
        recipePanel.add(textPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate Recipe");
        generateButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(generateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if we need to fetch a new batch of recipes
                if (currentRecipeIndex >= currentRecipes.size()) {
                    try {
                        currentRecipes = Random_recipe();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    currentRecipeIndex = 0;
                    if (currentRecipes == null || currentRecipes.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No more recipes available!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Get the next recipe
                Recipe selectedRecipe = currentRecipes.get(currentRecipeIndex++);
                recipeTitle.setText(selectedRecipe.getTitle());
                try {
                    recipeIngredients.setText(""); // Clear previous ingredients
                    for (Ingredient ingredient : selectedRecipe.get_ingredients()) {
                        recipeIngredients.append(ingredient.getName() + "\n");
                    }
                }
                    catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    recipeSteps.setText(selectedRecipe.getInstructions());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Load and set image (placeholder if no image available)
                ImageIcon imageIcon = new ImageIcon(selectedRecipe.getImage());
                Image scaledImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
}

    // Simulated Random_recipe function to fetch 10 recipes
//    private static List<Recipe> Random_recipe() {
//        // Replace this implementation with the actual function call
//        return List.of(
//                new Recipe("Spaghetti Bolognese", "Ingredients:\n- Pasta\n- Minced meat\n- Tomato sauce\n- Garlic\n- Onions",
//                        "Steps:\n1.