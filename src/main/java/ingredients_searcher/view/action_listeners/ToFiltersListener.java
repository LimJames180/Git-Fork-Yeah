package ingredients_searcher.view.action_listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;

import filter.data_access.FilterDataAccess;
import filter.interface_adapter.FilterPresenter;
import filter.interface_adapter.FilterViewModel;
import filter.use_case.FilterInteractor;
import filter.view.ToggleButtonsView;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

/**
 * Listener for the button which leads to the filter screen.
 */
public class ToFiltersListener extends Frame implements ActionListener {
    private List<String> ingredientsList;
    private IngredientSearchView isv;
    private SessionService currentSession;

    public ToFiltersListener(List<String> ingredientList, IngredientSearchView isv, SessionService currentSession) {
        this.ingredientsList = ingredientList;
        this.isv = isv;
        this.currentSession = currentSession;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            final ToggleButtonsView example = new ToggleButtonsView(ingredientsList, currentSession);
            example.setVisible(true);
        });

        isv.dispose();
    }
}
