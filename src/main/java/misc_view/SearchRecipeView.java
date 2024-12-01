package misc_view;

// ingredient search things
import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.view.IngredientSearchView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import login.app.SessionService;

public class SearchRecipeView extends JFrame {
    // UI Components
    private JButton addFilterButton;
    private JButton myIngredientsButton;
    private JPanel recipesPanel;
    private JScrollPane scrollPane;
    private SessionService currentSession;

    public SearchRecipeView(SessionService currentSession) {
        this.currentSession = currentSession;
        // Setting up the frame
        setTitle("entity.Recipe Finder - Search Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        // Heading
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        JLabel headingLabel = new JLabel("Result Recipes", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headingLabel);

        // Add Filter Button
        addFilterButton = new JButton("Add Filter");
        addFilterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        headerPanel.add(addFilterButton);

        add(headerPanel, BorderLayout.NORTH);

        // Panel to hold recipes
        recipesPanel = new JPanel();
        recipesPanel.setLayout(new BoxLayout(recipesPanel, BoxLayout.Y_AXIS));

        // Example recipes data (replace with dynamic data)
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Italian Tomato Pasta", "https://spoonacular.com/recipeImages/1-312x231.jpg"));
        recipes.add(new Recipe("Tomato Salad", "https://spoonacular.com/recipeImages/2-312x231.jpg"));

        // Add each recipe to the panel
        for (Recipe recipe : recipes) {
            addRecipeToPanel(recipe);
        }

        // Scroll Pane for recipes
        scrollPane = new JScrollPane(recipesPanel);
        add(scrollPane, BorderLayout.CENTER);

        // My Ingredients Button at the bottom
        myIngredientsButton = new JButton("My Ingredients");
        myIngredientsButton.addActionListener(e -> {
            // Logic to navigate to recipe exploration view
            IngredientDataAccess ingDataAccess = new IngredientDataAccess();
            AddIngredientViewModel viewModel = new AddIngredientViewModel(ingDataAccess);
            IngredientDataAccess dataAccess = new IngredientDataAccess();
            AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
            AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
            AddIngredientController controller = new AddIngredientController(interactor);
            new IngredientSearchView(null, currentSession, controller, viewModel);
        });
        add(myIngredientsButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addRecipeToPanel(Recipe recipe) {
        // Panel for each recipe
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BorderLayout());
        recipePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // entity.Recipe Image
        try {
            URL imageUrl = new URL(recipe.getImageUrl());
            ImageIcon imageIcon = new ImageIcon(imageUrl);

            Image image = imageIcon.getImage(); // Transform it into an Image object
            Image resizedImage = image.getScaledInstance(10, 10, Image.SCALE_SMOOTH); // Adjust the width and height
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            JLabel imageLabel = new JLabel(imageIcon);
            recipePanel.add(imageLabel, BorderLayout.WEST);
        } catch (Exception e) {
            e.printStackTrace();
            JLabel imageLabel = new JLabel("No Image");
            recipePanel.add(imageLabel, BorderLayout.WEST);
        }

        // entity.Recipe Name
        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(nameLabel, BorderLayout.CENTER);

        // Cook Button
        JButton cookButton = new JButton("Cook");
        recipePanel.add(cookButton, BorderLayout.EAST);

        // Add separator
        recipesPanel.add(recipePanel);
        recipesPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
    }

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(SearchRecipeView::new);
    //}

    // Inner class to represent a entity.Recipe (replace with your data structure)
    class Recipe {
        private String name;
        private String imageUrl;

        public Recipe(String name, String imageUrl) {
            this.name = name;
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
