package ingredients_searcher.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.view.action_listeners.AddIngredientListener;
import ingredients_searcher.view.action_listeners.IngredientsListener;
import ingredients_searcher.view.action_listeners.RandomListener;
import ingredients_searcher.view.action_listeners.ToFiltersListener;
import login.app.SessionService;

/**
 * This class sets up the UI for the ingredient searcher.
 */
public class IngredientSearchView extends JFrame {
    // UI Components
    private static final int FRAME_WIDTH = 650;
    private static final int FRAME_HEIGHT = 700;
    private static final int MAX_TEXT = 15;
    private JTextField ingredientInputField;
    private JLabel ingredientImageLabel;
    private JLabel ingredientNameLabel;
    private DefaultListModel<String> ingredientListModel;
    private JButton toFiltersButton;
    private JButton searchButton;
    private JButton addButton;
    private JButton randomButton;

    // controller and view model
    private AddIngredientController controller;
    private AddIngredientViewModel viewModel;

    /**
     * This class sets up the window.
     * @param ingredients The list of ingredients.
     * @param currentUser The current session.
     * @param controller The controller for the class.
     * @param viewModel The view model for the class.
     */
    public IngredientSearchView(List<String> ingredients, SessionService currentUser,
                                AddIngredientController controller, AddIngredientViewModel viewModel) {
        // list of ingredients, given parameter it needs to update the list
        final List<String> ingredientsList = Objects.requireNonNullElseGet(ingredients, ArrayList::new);

        // controller and view model
        this.controller = controller;
        this.viewModel = viewModel;

        uiSetup();
        listenersSetup(ingredientsList, currentUser);
    }

    private void uiSetup() {
        // add a button for random recipe
        setTitle("entity.Ingredient Search");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BorderLayout());

        // Top Panel for entity.Ingredient Input
        final JPanel inputPanel = new JPanel(new FlowLayout());
        ingredientInputField = new JTextField(MAX_TEXT);
        searchButton = new JButton("Search");
        inputPanel.add(new JLabel("Give us what you have:"));
        inputPanel.add(ingredientInputField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        // Display Panel for Search Results
        final JPanel displayPanel = new JPanel(new BorderLayout());
        ingredientImageLabel = new JLabel("", SwingConstants.CENTER);
        ingredientNameLabel = new JLabel("", SwingConstants.CENTER);
        displayPanel.add(ingredientImageLabel, BorderLayout.CENTER);
        displayPanel.add(ingredientNameLabel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.CENTER);

        // Add Button
        addButton = new JButton("Add");
        // Button is initially disabled
        addButton.setEnabled(false);
        add(addButton, BorderLayout.SOUTH);

        // List Panel for Current Ingredients
        final JPanel listPanel = new JPanel(new BorderLayout());
        ingredientListModel = new DefaultListModel<>();
        final JList<String> ingredientList = new JList<>(ingredientListModel);
        final JScrollPane listScrollPane = new JScrollPane(ingredientList);
        listPanel.add(new JLabel("Current Ingredients:"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Buttons for Searching Recipes and Exploring All Recipes
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        toFiltersButton = new JButton("Set Filters");
        randomButton = new JButton("Random Recipe");

        buttonPanel.add(toFiltersButton);
        buttonPanel.add(randomButton);

        // Combine Panels
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.EAST);

        setVisible(true);
    }

    /**
     * A void function which constructs all the button listeners.
     * @param ingredients The list of ingredients.
     * @param currentSession The current session.
     */
    public void listenersSetup(List<String> ingredients, SessionService currentSession) {
        toFiltersButton.addActionListener(new ToFiltersListener(ingredients, this, currentSession));
        searchButton.addActionListener(new IngredientsListener(ingredientInputField, this, viewModel, controller,
                ingredientNameLabel, ingredientImageLabel, addButton));
        addButton.addActionListener(new AddIngredientListener(ingredientListModel, ingredients, ingredientNameLabel,
                ingredientImageLabel, addButton));
        randomButton.addActionListener(new RandomListener(currentSession, this));
    }

}
