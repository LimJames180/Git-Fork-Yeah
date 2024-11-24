package view.ActionListeners;

import view.IngredientSearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientsListener implements ActionListener {
    private String query;
    private IngredientSearchView isv;

    public IngredientsListener(String query, IngredientSearchView isv) {
        this.query = query;
        this.isv = isv;
    }

    public void actionPerformed(ActionEvent e) {
        // String query = ingredientInputField.getText().trim();

        if (!query.isEmpty()) {
            isv.fetchIngredientData(query);
        }
        else {
            JOptionPane.showMessageDialog(isv,
                    "Please enter an ingredient to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
