package view.ActionListeners;

import data_access.FilterDataAccess;
import interface_adapter.filter.FilterController;
import use_case.filter.FilterInteractor;
import view.IngredientSearchView;
import view.ToggleButtonsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchListener extends Frame implements ActionListener {
    private List<String> ingredientsList;
    private IngredientSearchView isv;

    public SearchListener(List<String> ingredientList, IngredientSearchView isv) {
        this.ingredientsList = ingredientList;
        this.isv = isv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            FilterDataAccess dataAccess = new FilterDataAccess();
            FilterInteractor interactor = new FilterInteractor(dataAccess);
            FilterController controller = new FilterController(interactor);
            ToggleButtonsView example = new ToggleButtonsView(ingredientsList, controller);
            example.setVisible(true);
        });

        this.isv.dispose();
    }
}
