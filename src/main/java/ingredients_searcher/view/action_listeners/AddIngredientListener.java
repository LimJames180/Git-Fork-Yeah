package ingredients_searcher.view.action_listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class adds the inputted ingredient to the relevant lists.
 */
public class AddIngredientListener implements ActionListener {
    String ingredientName;
    List<String> ingredientsList; // instead of this, i should be having a controller
    // call the interactor which updates the list?
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
            ingredientsList.add(ingredientName); // for here, i should be calling the controller(?) and using a method from that to update the list
            ingredientListModel.addElement(ingredientName);
            ingredientNameLabel.setText(""); // Clear displayed ingredient
            ingredientImageLabel.setIcon(null); // Clear displayed image
            addButton.setEnabled(false); // Disable add button
        }
    }
}
