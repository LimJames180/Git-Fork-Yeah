package ingredients_searcher.view.action_listeners;

import ingredients_searcher.interface_adapter.AddIngredientController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class adds the inputted ingredient to the relevant lists.
 */
public class AddIngredientListener implements ActionListener {
    private String ingredientName;
    private List<String> ingredientsList;
    // instead of this, i should be having a controller
    // call the interactor which updates the list?
    private final DefaultListModel<String> ingredientListModel;
    private final JLabel ingredientNameLabel;
    private final JLabel ingredientImageLabel;
    private final JButton addButton;

    public AddIngredientListener(DefaultListModel<String> ingredientListModel, List<String> ingredientList,
                                 JLabel ingredientNameLabel, JLabel ingredientImageLabel, JButton addButton,
                                 AddIngredientController controller) {
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
            // Clear displayed ingredient and image, turn off the add button.
            ingredientNameLabel.setText("");
            ingredientImageLabel.setIcon(null);
            addButton.setEnabled(false);
        }
    }
}
