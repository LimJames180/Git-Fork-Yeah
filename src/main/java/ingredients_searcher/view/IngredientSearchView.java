package ingredients_searcher.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.view.action_listeners.AddIngredientListener;
import ingredients_searcher.view.action_listeners.IngredientsListener;
import ingredients_searcher.view.action_listeners.RandomListener;
import ingredients_searcher.view.action_listeners.ToFiltersListener;
import login.app.SessionService;
import RandomFYP.view.RandomView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class IngredientSearchView extends JFrame {
    // UI Components
    private JTextField ingredientInputField;
    private JLabel ingredientImageLabel;
    private JLabel ingredientNameLabel;
    private DefaultListModel<String> ingredientListModel;
    private JButton toFiltersButton;
    private JButton searchButton;
    private JButton addButton;
    private JButton randomButton;

    //controller and view model
    private AddIngredientController controller;
    private AddIngredientViewModel viewModel;

    // Spoonacular API Details
    private static final String SPOONACULAR_API_KEY = "62fb1e66d4be4351b17b5f5043ede6db"; // Replace with your actual API key
    private static final String SPOONACULAR_SEARCH_URL = "https://api.spoonacular.com/food/ingredients/search?query=%s&apiKey=%s";

    /**
     * This class sets up the window.
     * @param ingredients the list of ingredients, empty if from previous screen.
     */
    public IngredientSearchView(List<String> ingredients, SessionService currentUser,
                                AddIngredientController controller, AddIngredientViewModel viewModel) {
        // list of ingredients
        List<String> ingredientsList = Objects.requireNonNullElseGet(ingredients, ArrayList::new);

        // controller and view model
        this.controller = controller;
        this.viewModel = viewModel;

        uiSetup();
        listenersSetup(ingredientsList, currentUser);
    }

    public void uiSetup() {
        // add a button for random recipe
        setTitle("entity.Ingredient Search");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH); // is there a way to add buffers on the sides of windows
        setLayout(new BorderLayout());

        // Top Panel for entity.Ingredient Input
        JPanel inputPanel = new JPanel(new FlowLayout());
        ingredientInputField = new JTextField(15);
        searchButton = new JButton("Search");
        inputPanel.add(new JLabel("Give us what you have:"));
        inputPanel.add(ingredientInputField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        // Display Panel for Search Results
        JPanel displayPanel = new JPanel(new BorderLayout());
        ingredientImageLabel = new JLabel("", SwingConstants.CENTER);
        ingredientNameLabel = new JLabel("", SwingConstants.CENTER);
        displayPanel.add(ingredientImageLabel, BorderLayout.CENTER);
        displayPanel.add(ingredientNameLabel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.CENTER);

        // Add Button
        addButton = new JButton("Add");
        addButton.setEnabled(false); // Initially disabled
        add(addButton, BorderLayout.SOUTH);

        // List Panel for Current Ingredients
        JPanel listPanel = new JPanel(new BorderLayout());
        ingredientListModel = new DefaultListModel<>();
        JList<String> ingredientList = new JList<>(ingredientListModel);
        JScrollPane listScrollPane = new JScrollPane(ingredientList);
        listPanel.add(new JLabel("Current Ingredients:"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Buttons for Searching Recipes and Exploring All Recipes
        JPanel buttonPanel = new JPanel(new FlowLayout());
        toFiltersButton = new JButton("Set Filters");
        randomButton = new JButton("Random Recipe");

        buttonPanel.add(toFiltersButton);
        buttonPanel.add(randomButton);

        // Combine Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.EAST);

        setVisible(true);
    }

    public void listenersSetup(List<String> ingredients, SessionService currentUser) {
        // need to revise the things here
        toFiltersButton.addActionListener(new ToFiltersListener(ingredients, this, currentUser));
        searchButton.addActionListener(new IngredientsListener(ingredientInputField, this, controller));
        addButton.addActionListener(new AddIngredientListener(ingredientListModel, ingredients, ingredientNameLabel,
                ingredientImageLabel, addButton));
        randomButton.addActionListener(new RandomListener(currentUser, this));
    }

}
