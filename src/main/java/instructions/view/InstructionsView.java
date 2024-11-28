package instructions.view;

import entity.Recipe;
import instructions.data_access.InstructionsDataAccess;
import instructions.interface_adapter.InstructionsController;
import instructions.interface_adapter.InstructionsPresenter;
import instructions.interface_adapter.InstructionsViewModel;
import instructions.use_case.InstructionsInteractor;
import filter.view.FilterView;
import interface_adapter.RecipeController;
import interface_adapter.SavedRecipeController;
import login.app.SessionService;
import login.data_access.MongoUserDataAccessImpl;
import login.data_access.UserDataAccess;
import login.entities.User;
import view.FilterView;
import view.LoggedInPageView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class InstructionsView extends JFrame {
    private InstructionsController instructionsController;
    private JButton backButton;
    private JButton saveButton;
    private InstructionsViewModel instructionsViewModel;
    private int id;
    private SessionService currentSession;
    private SavedRecipeController savedRecipeController;
    private UserDataAccess userDataAccess;


    public InstructionsView(int id, FilterView filterView, SessionService currentSession) throws IOException {
        this.id = id;
        this.currentSession = currentSession;
        initializeView();
        setupUI(filterView);

    }

    public InstructionsView(int id, LoggedInPageView loggedInPageView, SessionService currentSession) {
        this.id = id;
        this.currentSession = currentSession;
        initializeView();
        setupUI(loggedInPageView);
    }

    private void initializeView() {
        this.instructionsViewModel = new InstructionsViewModel();
        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();
        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
        InstructionsInteractor instructionsInteractor = new InstructionsInteractor(instructionsDataAccess,instructionsPresenter);

        this.instructionsController = new InstructionsController(instructionsInteractor);
        this.userDataAccess = new MongoUserDataAccessImpl();
    }

    private void setupUI(BaseView filterView) {
        instructionsController.handleInstructions(id);
        setTitle("Recipe Instructions"); // set as name of recipe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setLayout(new FlowLayout());


        // Middle panel for ingredients list from API
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BorderLayout());
        JTextArea ingredientsTextArea = new JTextArea(instructionsViewModel.getIngredients());
        ingredientsTextArea.setLineWrap(true);
        ingredientsTextArea.setWrapStyleWord(true);
        ingredientsTextArea.setSize(800, 200);
        ingredientsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ingredientsPanel.add(ingredientsLabel, BorderLayout.NORTH);
        ingredientsPanel.add(ingredientsTextArea, BorderLayout.CENTER);


        // Bottom panel for instructions from API
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BorderLayout());
        JTextArea instructionsTextArea = new JTextArea(instructionsViewModel.getInstructions());
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true);
        instructionsTextArea.setSize(800, 200);
        instructionsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Top panel for recipe image
        JLabel imageLabel = new JLabel();
        JPanel imagePanel = new JPanel();

        try {
            URL imageUrl = new URL(instructionsViewModel.getImage());
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(imageUrl)
                            .getImage()
                            .getScaledInstance(125, 125, Image.SCALE_SMOOTH)
            );
            imageLabel.setIcon(icon);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Error loading image for recipe");
            e.printStackTrace();
        }

        // Panel for back button
        backButton = new JButton("Back");
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        // Panel for nutritional information
        JPanel nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BorderLayout());
        JTextArea nutritionTextArea = new JTextArea(instructionsViewModel.getNutrients());
        nutritionTextArea.setLineWrap(true);
        nutritionTextArea.setWrapStyleWord(true);
        nutritionTextArea.setSize(800, 200);
        nutritionTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel nutritionLabel = new JLabel("Nutritional information:");
        nutritionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nutritionPanel.add(nutritionLabel);
        nutritionPanel.add(nutritionTextArea);

        instructionsPanel.add(instructionsLabel, BorderLayout.NORTH);
        instructionsPanel.add(instructionsTextArea, BorderLayout.CENTER);
        instructionsPanel.add(nutritionPanel, BorderLayout.SOUTH);
        instructionsPanel.add(imagePanel, BorderLayout.EAST);

        // Back button logic
        backButton.addActionListener(e -> {
            dispose(); // Disposes the current JFrame
            filterView.setVisible(true);
        });

        // Panel for save button
        saveButton = new JButton("Save Recipe!");
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.add(saveButton);

        // Save button logic
        saveButton.addActionListener(e -> {
            userDataAccess.saveRecipeForUser(currentSession.getUsername(), new Recipe(Integer.toString(id)));
            JOptionPane.showMessageDialog(this, "Recipe saved successfully!");
//            Recipe recipeToSave = new Recipe(String.valueOf(id));
//            savedRecipeController.saveRecipe(currentSession.getUsername(), recipeToSave);
//            JOptionPane.showMessageDialog(this, "Recipe saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(saveButton, BorderLayout.EAST);

        // Add everything to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(ingredientsPanel, BorderLayout.NORTH);
        mainPanel.add(instructionsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setVisible(true);
    }
}
