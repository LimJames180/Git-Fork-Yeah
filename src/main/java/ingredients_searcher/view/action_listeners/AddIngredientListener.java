package ingredients_searcher.view.action_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import ingredients_searcher.interface_adapter.AddIngredientController;

/**
 * This class adds the inputted ingredient to the relevant lists.
 */
public class AddIngredientListener implements ActionListener {
    private String ingredientName;
    private List<String> ingredientsList;
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
