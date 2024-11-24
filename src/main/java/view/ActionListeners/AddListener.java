package view.ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddListener implements ActionListener {
    String ingredientName;
    List<String> ingredientsList;
    private DefaultListModel<String> ingredientListModel;
    private JLabel ingredientNameLabel;
    private JLabel ingredientImageLabel;
    private JButton addButton;

    public AddListener(String ingredientName, DefaultListModel<String> ingredientListModel, List<String> ingredientList, JLabel ingredientNameLabel,
                       JLabel ingredientImageLabel, JButton addButton) {
        this.ingredientName = ingredientName;
        this.ingredientListModel = ingredientListModel;
        this.ingredientsList = ingredientList;
        this.ingredientNameLabel = ingredientNameLabel;
        this.ingredientImageLabel = ingredientImageLabel;
        this.addButton = addButton;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ingredientName = ingredientNameLabel.getText();
        if (!ingredientName.isEmpty()) {
            ingredientsList.add(ingredientName);
            ingredientListModel.addElement(ingredientName);
            ingredientNameLabel.setText(""); // Clear displayed ingredient
            ingredientImageLabel.setIcon(null); // Clear displayed image
            addButton.setEnabled(false); // Disable add button
        }
    }
}
