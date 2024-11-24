package view.ActionListeners;

import view.IngredientSearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientsListener implements ActionListener {
    final private String query;
    final private IngredientSearchView isv;

    public IngredientsListener(String query, IngredientSearchView isv) {
        this.query = query;
        this.isv = isv;
    }

    public void actionPerformed(ActionEvent e) {
        if (!query.isEmpty()) {
            isv.fetchIngredientData(query);
        }
        else {
            JOptionPane.showMessageDialog(isv,
                    "Please enter an ingredient to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
