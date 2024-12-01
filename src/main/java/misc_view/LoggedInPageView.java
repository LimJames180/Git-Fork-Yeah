package misc_view;

import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.view.IngredientSearchView;
import entity.Recipe;
import instructions.view.BaseView;
import instructions.view.InstructionsView;
import login.app.SessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoggedInPageView extends JFrame implements BaseView {
    // UI Components
    private JLabel welcomeLabel;
    private JList<String> savedRecipesList;
    private DefaultListModel<String> savedRecipesModel;
    private JButton exploreRecipesButton;
    private JPanel recipesPanel;

    public LoggedInPageView(String username, List<Recipe> savedRecipes, SessionService currentSession) {
        //System.out.println(savedRecipes.toString());
        setTitle("Recipe Finder - Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome Back, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        recipesPanel = new JPanel();
        recipesPanel.setLayout(new GridLayout(0, 1));
        if (savedRecipes != null) {
            for (Recipe recipe : savedRecipes) {
                String recipeName = recipe.getRecipeName(recipe);
                JButton recipeButton = new JButton(recipeName);
                recipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                recipeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new InstructionsView(recipe.getId(), new LoggedInPageView(username, savedRecipes, currentSession), currentSession);
                    }
                });
                recipesPanel.add(recipeButton);
            }
        }

        JScrollPane recipeScrollPane = new JScrollPane(recipesPanel);
        recipeScrollPane.setBorder(BorderFactory.createTitledBorder("Saved Recipes"));
        add(recipeScrollPane, BorderLayout.CENTER);


        exploreRecipesButton = new JButton("Explore Recipes");
        exploreRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IngredientDataAccess ingDataAccess = new IngredientDataAccess();
                AddIngredientViewModel viewModel = new AddIngredientViewModel(ingDataAccess);
                IngredientDataAccess dataAccess = new IngredientDataAccess();
                AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
                AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
                AddIngredientController controller = new AddIngredientController(interactor);
                new IngredientSearchView(null, currentSession, controller, viewModel);
            }
        });
        add(exploreRecipesButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}

