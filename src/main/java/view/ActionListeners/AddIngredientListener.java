package view.ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class adds the inputted ingredient to the relevant lists.
 */
public class AddIngredientListener implements ActionListener {
    String ingredientName;
    List<String> ingredientsList;
    final private DefaultListModel<String> ingredientListModel;
    final private JLabel ingredientNameLabel;
    final private JLabel ingredientImageLabel;
    final private JButton addButton;

    public AddIngredientListener(DefaultListModel<String> ingredientListModel, List<String> ingredientList, JLabel ingredientNameLabel,
                                 JLabel ingredientImageLabel, JButton addButton) {
        this.ingredientListModel = ingredientListModel;
        this.ingredientsList = ingredientList;
        this.ingredientNameLabel = ingredientNameLabel;
        this.ingredientImageLabel = ingredientImageLabel;
        this.addButton = addButton;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ingredientName = ingredientNameLabel.getText();
        if (!ingredientName.isEmpty()) {
            ingredientsList.add(ingredientName);
            ingredientListModel.addElement(ingredientName);
            ingredientNameLabel.setText(""); // Clear displayed ingredient
            ingredientImageLabel.setIcon(null); // Clear displayed image
            addButton.setEnabled(false); // Disable add button
        }
    }
}
