package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
    private JButton searchRecipesButton;
    private JButton exploreAllRecipesButton;

    // Temporary storage for ingredients
    private List<String> temporaryIngredients;

    // Spoonacular API Details
    private static final String SPOONACULAR_API_KEY = "62fb1e66d4be4351b17b5f5043ede6db"; // Replace with your actual API key
    private static final String SPOONACULAR_SEARCH_URL = "https://api.spoonacular.com/food/ingredients/search?query=%s&apiKey=%s";

    public IngredientSearchView() {
        // Initialize temporary storage
        temporaryIngredients = new ArrayList<>();

        // Setting up the frame
        setTitle("Ingredient Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new BorderLayout());

        // Top Panel for Ingredient Input
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
        searchRecipesButton = new JButton("Search Recipes");
        searchRecipesButton.addActionListener(e -> new SearchRecipeView());

        exploreAllRecipesButton = new JButton("Explore All Recipes");
        exploreAllRecipesButton.addActionListener(e -> new SearchRecipeView());

        buttonPanel.add(searchRecipesButton);
        buttonPanel.add(exploreAllRecipesButton);

        // Combine Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.EAST);

        // Event Listeners
        searchButton.addActionListener(e -> {
            String query = ingredientInputField.getText().trim();
            if (!query.isEmpty()) {
                fetchIngredientData(query);
            } else {
                JOptionPane.showMessageDialog(IngredientSearchView.this, "Please enter an ingredient to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addButton.addActionListener(e -> {
            String ingredientName = ingredientNameLabel.getText();
            if (!ingredientName.isEmpty()) {
                temporaryIngredients.add(ingredientName);
                ingredientListModel.addElement(ingredientName);
                ingredientNameLabel.setText(""); // Clear displayed ingredient
                ingredientImageLabel.setIcon(null); // Clear displayed image
                addButton.setEnabled(false); // Disable add button
            }
        });

        setVisible(true);
    }

    private void fetchIngredientData(String query) {
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
        SwingUtilities.invokeLater(IngredientSearchView::new);
    }
}
