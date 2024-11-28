package ingredients_searcher.view.action_listeners;

import ingredients_searcher.view.IngredientSearchView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This ActionListener takes the input and checks if it is valid.
 */
public class IngredientsListener extends Frame implements ActionListener {
    private String query;
    private final IngredientSearchView isv;
    private JTextField searchField;

    public IngredientsListener(JTextField ingredient, IngredientSearchView isv) {
        this.isv = isv;
        this.searchField = ingredient;
    }

    public void actionPerformed(ActionEvent e) {
        query = searchField.getText().trim();
        if (!query.isEmpty()) {
            isv.fetchIngredientData(query);
        }
        else {
            JOptionPane.showMessageDialog(isv,
                    "Please enter an ingredient to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
