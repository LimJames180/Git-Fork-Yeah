package ingredients_searcher.view.action_listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Ingredient;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.view.IngredientSearchView;

/**
 * This ActionListener takes the input and checks if it is valid.
 */
public class IngredientsListener extends Frame implements ActionListener {
    private String query;
    private final IngredientSearchView isv;
    private JTextField searchField;
    private final AddIngredientController controller;
    private final JLabel ingredientNameLabel;
    private final JLabel ingredientImageLabel;
    private final JButton addButton;

    public IngredientsListener(JTextField ingredient, IngredientSearchView isv, AddIngredientViewModel viewModel,
                               AddIngredientController controller, JLabel nameLabel, JLabel imageLabel,
                               JButton button) {
        this.isv = isv;
        this.searchField = ingredient;
        this.controller = controller;
        this.ingredientNameLabel = nameLabel;
        this.ingredientImageLabel = imageLabel;
        this.addButton = button;
    }

    /**
     * Activates the ingredient search.
     * @param event the event to be processed.
     */
    public void actionPerformed(ActionEvent event) {
        query = searchField.getText().trim();
        if (!query.isEmpty()) {
            final Ingredient ingredient = controller.ingredientSearch(query);

            if (ingredient.getName() != null || !ingredient.getName().isEmpty()) {
                // Display ingredient information
                ingredientNameLabel.setText(ingredient.getName());
                try {
                    ingredientImageLabel.setIcon(new ImageIcon(new URL(ingredient.getImage())));
                }
                catch (MalformedURLException error) {
                    throw new RuntimeException(error);
                }
                addButton.setEnabled(true);
            }
            else {
                ingredientNameLabel.setText("No results found.");
                ingredientImageLabel.setIcon(null);
                addButton.setEnabled(false);
            }

        }
        else {
            JOptionPane.showMessageDialog(isv,
                    "Please enter an ingredient to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
