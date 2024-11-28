package ingredients_searcher.view;

import javax.swing.*;
import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ingredients_searcher.view.action_listeners.AddIngredientListener;
import ingredients_searcher.view.action_listeners.IngredientsListener;
import ingredients_searcher.view.action_listeners.ToFiltersListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class IngredientSearchView extends JFrame {
    // UI Components
    private JTextField ingredientInputField;
    private JButton searchButton;
    private JLabel ingredientImageLabel;
    private JLabel ingredientNameLabel;
    private JButton addButton;
    private DefaultListModel<String> ingredientListModel;
    private JList<String> ingredientList;
    private JButton toFiltersButton;

    // Spoonacular API Details
    private static final String SPOONACULAR_API_KEY = "62fb1e66d4be4351b17b5f5043ede6db"; // Replace with your actual API key
    private static final String SPOONACULAR_SEARCH_URL = "https://api.spoonacular.com/food/ingredients/search?query=%s&apiKey=%s";

    /**
     * This class sets up the window.
     * @param ingredients the list of ingredients, empty if from previous screen.
     */
    public IngredientSearchView(List<String> ingredients) {
        List<String> ingredientsList = Objects.requireNonNullElseGet(ingredients, ArrayList::new);
        // list of ingredients

        // Setting up the frame
        setTitle("entity.Ingredient Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
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
        ingredientList = new JList<>(ingredientListModel);
        JScrollPane listScrollPane = new JScrollPane(ingredientList);
        listPanel.add(new JLabel("Current Ingredients:"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Buttons for Searching Recipes and Exploring All Recipes
        JPanel buttonPanel = new JPanel(new FlowLayout());
        toFiltersButton = new JButton("Set Filters");

        buttonPanel.add(toFiltersButton);

        // Combine Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.EAST);

        // Event Listeners
        toFiltersButton.addActionListener(new ToFiltersListener(ingredientsList, this));
        searchButton.addActionListener(new IngredientsListener(ingredientInputField, this));
        addButton.addActionListener(new AddIngredientListener(ingredientListModel, ingredientsList, ingredientNameLabel,
                ingredientImageLabel, addButton));

        setVisible(true);
    }

    /**
     * Access the API for the ingredients.
     * @param query the given ingredient to search.
     */
    public void fetchIngredientData(String query) {
        try {
            // Construct the API URL
            String urlString = String.format(SPOONACULAR_SEARCH_URL, query, SPOONACULAR_API_KEY);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            // Parse and display ingredient data
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONArray results = jsonResponse.getJSONArray("results");
            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                String name = firstResult.getString("name");
                String imageUrl = "https://spoonacular.com/cdn/ingredients_100x100/" + firstResult.getString("image");

                // Display ingredient information
                ingredientNameLabel.setText(name);
                ingredientImageLabel.setIcon(new ImageIcon(new URL(imageUrl)));
                addButton.setEnabled(true);
            } else {
                ingredientNameLabel.setText("No results found.");
                ingredientImageLabel.setIcon(null);
                addButton.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching ingredient data.", "API Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        IngredientSearchView isv = new IngredientSearchView(null);
        // SwingUtilities.invokeLater((Runnable) isv);
        SwingUtilities.invokeLater(() -> isv.setVisible(true));
    }
}