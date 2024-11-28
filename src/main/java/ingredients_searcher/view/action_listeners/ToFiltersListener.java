package ingredients_searcher.view.action_listeners;

import filter.data_access.FilterDataAccess;
import filter.interface_adapter.FilterPresenter;
import filter.interface_adapter.FilterViewModel;
import ingredients_searcher.view.IngredientSearchView;
import filter.interface_adapter.FilterController;
import filter.use_case.FilterInteractor;
import filter.view.ToggleButtonsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToFiltersListener extends Frame implements ActionListener {
    private List<String> ingredientsList;
    private IngredientSearchView isv;

    public ToFiltersListener(List<String> ingredientList, IngredientSearchView isv) {
        this.ingredientsList = ingredientList;
        this.isv = isv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            FilterDataAccess dataAccess = new FilterDataAccess();
            FilterViewModel filterViewModel = new FilterViewModel();
            FilterPresenter presenter = new FilterPresenter(filterViewModel);
            FilterInteractor interactor = new FilterInteractor(dataAccess, presenter);
            FilterController controller = new FilterController(interactor);
            ToggleButtonsView example = new ToggleButtonsView(ingredientsList);
            example.setVisible(true);
        });

        this.isv.dispose();
    }
}
