package filter.view;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.jetbrains.annotations.NotNull;

import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

/**
 * The ToggleButtonsView class represents the GUI for the toggle buttons.
 * It extends JFrame.
 */
public class ToggleButtonsView extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private Map<String, Boolean> variables = new HashMap<>();
    private Map<String, Boolean> variables2 = new HashMap<>();

    public ToggleButtonsView(List<String> ingredients, SessionService currentSession) {
        setTitle("Multiple Toggle Buttons Example");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        final String[] buttonNames = {"glutenfree", "vegetarian", "vegan", "ketogenic"};
        for (String name : buttonNames) {
            variables.put(name, false);
            final JButton toggleButton = new JButton(name.substring(0, 1).toUpperCase() + name.substring(1));
            toggleButton.addActionListener(event -> {
                variables.put(name, !variables.get(name));
                if (variables.get(name)) {
                    toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + " (ON)");
                }
                else {
                    toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + " (OFF)");
                }
            });
            add(toggleButton);
        }

        final String[] buttonNames2 = {"dairy", "egg", "peanut", "seafood"};
        for (String name : buttonNames2) {
            variables2.put(name, false);
            final JButton toggleButton = new JButton(name.substring(0, 1).toUpperCase() + name.substring(1) + " Free");
            toggleButton.addActionListener(event -> {
                variables2.put(name, !variables2.get(name));
                if (variables2.get(name)) {
                    toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + " Free (ON)");
                }
                else {
                    toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + " Free (OFF)");
                }
            });
            add(toggleButton);
        }

        final JButton switchButton = new JButton("Done");
        switchButton.addActionListener(event -> {
            dispose();
            final FilterView filterSwing = new FilterView(ingredients, ToggleButtonsView.this, currentSession);
            filterSwing.setVisible(true);
        });
        add(switchButton);

        final JButton backButton = getjButton(ingredients, currentSession);
        add(backButton);

    }

    @NotNull
    private JButton getjButton(List<String> ingredients, SessionService currentSession) {
        final JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> backButtonEvent(ingredients, currentSession));
        return backButton;
    }

    private void backButtonEvent(List<String> ingredients, SessionService currentSession) {
        dispose();
        final IngredientDataAccess ingDataAccess = new IngredientDataAccess();
        final AddIngredientViewModel viewModel = new AddIngredientViewModel(ingDataAccess);
        final IngredientDataAccess dataAccess = new IngredientDataAccess();
        final AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
        final AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
        final AddIngredientController controller = new AddIngredientController(interactor);
        final IngredientSearchView ingredientSearchView = new IngredientSearchView(ingredients,
                currentSession, controller, viewModel);
        ingredientSearchView.setVisible(true);
    }

    public Map<String, Boolean> getVariables() {
        return variables;
    }

    public Map<String, Boolean> getVariables2() {
        return variables2;
    }
}
