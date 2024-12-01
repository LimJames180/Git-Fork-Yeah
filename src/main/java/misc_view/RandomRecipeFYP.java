package misc_view;

import entity.Ingredient;
import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import static misc_interface_adapter.RecipeDataAccess.randomRecipe;


public class RandomRecipeFYP {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new RandomRecipeFYP().createAndShowGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createAndShowGUI() throws IOException {
        // Frame setup
        JFrame frame = new JFrame("Random entity.Recipe FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Random entity.Recipe For You", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // entity.Recipe display area
        JTextArea recipeTextArea = new JTextArea();
        recipeTextArea.setEditable(false);
        recipeTextArea.setLineWrap(true);
        recipeTextArea.setWrapStyleWord(true);
        recipeTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        recipeTextArea.setText(getRandomRecipe());
        JScrollPane scrollPane = new JScrollPane(recipeTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton randomizeButton = new JButton("Randomize entity.Recipe");
        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    recipeTextArea.setText(getRandomRecipe());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonPanel.add(randomizeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the GUI
        frame.setVisible(true);
    }


    // Placeholder for getting a random recipe
    private String getRandomRecipe() throws IOException {
        List<Recipe> recipelist = randomRecipe();
        Recipe recipe = recipelist.get(0);


        String recipeName = recipe.getTitle();
        String recipeDescription = "Desciption";
        StringBuilder ingredients = new StringBuilder();

        for (Ingredient ingredient : recipe.get_ingredients()) {
            ingredients.append(ingredient.getName() + ("\n"));
        }

        String recipeIngredients = String.valueOf(ingredients);


        String recipeSteps = "Steps";

        return recipeName + "\n\n" + recipeDescription + "\n\n" + recipeIngredients + "\n\n" + recipeSteps;
    }
}