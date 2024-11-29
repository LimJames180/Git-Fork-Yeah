package view;

import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInPageView extends JFrame {
    // UI Components
    private JLabel welcomeLabel;
    private JList<String> savedRecipesList;
    private JButton exploreRecipesButton;
    private DefaultListModel<String> savedRecipesModel;

    public LoggedInPageView(String username) {
        // Setting up the frame
        setTitle("entity.Recipe Finder - Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Welcome Message
        welcomeLabel = new JLabel("Welcome Back, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        // Saved Recipes List
        savedRecipesModel = new DefaultListModel<>();
        // Placeholder for saved recipes fetched from Spoonacular API (using example data)
        savedRecipesModel.addElement("Tomato Pasta - entity.Recipe ID: 12345");
        savedRecipesModel.addElement("Another Saved entity.Recipe - entity.Recipe ID: 67890"); // Example placeholder

        savedRecipesList = new JList<>(savedRecipesModel);
        savedRecipesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane recipeScrollPane = new JScrollPane(savedRecipesList);
        recipeScrollPane.setBorder(BorderFactory.createTitledBorder("Saved Recipes"));
        add(recipeScrollPane, BorderLayout.CENTER);

        // Explore Recipes Button
        exploreRecipesButton = new JButton("Explore Recipes");
        exploreRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to navigate to recipe exploration view
//                JOptionPane.showMessageDialog(LoggedInPageView.this, "Navigating to Explore Recipes...");
                SessionService currentUser = new SessionService();
                IngredientDataAccess ingDataAccess = new IngredientDataAccess();
                AddIngredientViewModel viewModel = new AddIngredientViewModel(ingDataAccess);
                IngredientDataAccess dataAccess = new IngredientDataAccess();
                AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
                AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
                AddIngredientController controller = new AddIngredientController(interactor);
                new IngredientSearchView(null, currentUser, controller, viewModel);
            }
        });
        add(exploreRecipesButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Example invocation with a sample username
        SwingUtilities.invokeLater(() -> new LoggedInPageView("SampleUser"));
    }
}

