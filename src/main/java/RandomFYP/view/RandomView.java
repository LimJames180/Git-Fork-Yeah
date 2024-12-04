package RandomFYP.view;

import RandomFYP.Static_Variables;
import RandomFYP.interface_adapter.RandomController;
import RandomFYP.interface_adapter.RandomPresenter;
import RandomFYP.interface_adapter.RandomViewModel;
import RandomFYP.use_case.RandomOutput;
import entity.Recipe;
import ingredients_searcher.data_access.IngredientDataAccess;
import ingredients_searcher.interface_adapter.AddIngredientController;
import ingredients_searcher.interface_adapter.AddIngredientPresenter;
import ingredients_searcher.interface_adapter.AddIngredientViewModel;
import ingredients_searcher.use_case.AddIngredientInteractor;
import ingredients_searcher.view.IngredientSearchView;
import login.app.SessionService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * The RandomView class represents the UI for generating random recipes.
 */
public class RandomView {
    private static final String FONT_ARIAL = "Arial";
    private static List<Recipe> currentRecipes;
    private static int currentRecipeIndex;
    private final JButton backButton;

    /**
     * Constructs a RandomView instance.
     *
     * @param currentSession the current session service
     * @throws IOException if an I/O error occurs
     */
    public RandomView(final SessionService currentSession) throws IOException {
//        loadRecipes();

        final JFrame frame = new JFrame("Random Recipe Generator");
        frame.setSize(Static_Variables.FRAME_WIDTH, Static_Variables.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        final JLabel titleLabel = new JLabel("Random Recipe Generator", JLabel.CENTER);
        titleLabel.setFont(new Font(FONT_ARIAL, Font.BOLD, Static_Variables.TITLE_FONT_SIZE));
        frame.add(titleLabel, BorderLayout.NORTH);

        final JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(recipePanel, BorderLayout.CENTER);

        final JLabel imageLabel = new JLabel("", JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(Static_Variables.IMAGE_WIDTH, Static_Variables.IMAGE_HEIGHT));
        recipePanel.add(imageLabel, BorderLayout.NORTH);

        final JTextArea recipeTitle = new JTextArea();
        recipeTitle.setFont(new Font(FONT_ARIAL, Font.BOLD, Static_Variables.RECIPE_TITLE_FONT_SIZE));
        recipeTitle.setEditable(false);
        recipeTitle.setWrapStyleWord(true);
        recipeTitle.setLineWrap(true);
        recipeTitle.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        final JTextArea recipeIngredients = new JTextArea();
        recipeIngredients.setFont(new Font(FONT_ARIAL, Font.PLAIN, Static_Variables.RECIPE_INGREDIENTS_FONT_SIZE));
        recipeIngredients.setEditable(false);
        recipeIngredients.setWrapStyleWord(true);
        recipeIngredients.setLineWrap(true);
        recipeIngredients.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        final JTextArea recipeSteps = new JTextArea();
        recipeSteps.setFont(new Font(FONT_ARIAL, Font.PLAIN, Static_Variables.RECIPE_STEPS_FONT_SIZE));
        recipeSteps.setEditable(false);
        recipeSteps.setWrapStyleWord(true);
        recipeSteps.setLineWrap(true);
        recipeSteps.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        final JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.add(recipeTitle);
        textPanel.add(new JScrollPane(recipeIngredients));
        textPanel.add(new JScrollPane(recipeSteps));
        recipePanel.add(textPanel, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        final JButton generateButton = new JButton("Generate Recipe");
        generateButton.setFont(new Font(FONT_ARIAL, Font.BOLD, Static_Variables.BUTTON_FONT_SIZE));
        buttonPanel.add(generateButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addGenerateButtonListener(generateButton, frame, recipeTitle, recipeIngredients, recipeSteps, imageLabel);

        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        buttonPanel.add(generateButton);

        backButton.addActionListener(event -> {
            frame.dispose();
            final AddIngredientViewModel viewModel = new AddIngredientViewModel();
            final IngredientDataAccess dataAccess = new IngredientDataAccess();
            final AddIngredientPresenter presenter = new AddIngredientPresenter(viewModel);
            final AddIngredientInteractor interactor = new AddIngredientInteractor(presenter, dataAccess);
            final AddIngredientController controller = new AddIngredientController(interactor);
            new IngredientSearchView(null, currentSession, controller, viewModel);
        });

        frame.setVisible(true);
    }

//    private static void loadRecipes() throws IOException {
//        final RandomController controller = new RandomController();
//        currentRecipes = controller.randomRecipe();
//        if (currentRecipes == null || currentRecipes.isEmpty()) {
//            throw new RuntimeException("No recipes found from randomRecipe!");
//        }
//    }

    private static void addGenerateButtonListener(final JButton generateButton, final JFrame frame,
                                                  final JTextArea recipeTitle, final JTextArea recipeIngredients,
                                                  final JTextArea recipeSteps, final JLabel imageLabel) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    handleGenerateButtonAction(frame, recipeTitle, recipeIngredients, recipeSteps, imageLabel);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private static void handleGenerateButtonAction(final JFrame frame, final JTextArea recipeTitle,
                                                   final JTextArea recipeIngredients, final JTextArea recipeSteps,
                                                   final JLabel imageLabel) throws IOException {
//        final Recipe selectedRecipe = currentRecipes.get(currentRecipeIndex++);
        updateRecipeDetails(recipeTitle, recipeIngredients, recipeSteps, imageLabel);
    }

//    private static void reloadRecipes() throws IOException {
//        final RandomController randomcontroller = new RandomController();
//        currentRecipes = randomcontroller.randomRecipe();
//        currentRecipeIndex = 0;
//    }

    private static void showErrorDialog(final JFrame frame, final String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void updateRecipeDetails(final JTextArea recipeTitle, final JTextArea recipeIngredients,
                                            final JTextArea recipeSteps, final JLabel imageLabel) throws IOException {

        final RandomController controller = new RandomController();
        final RandomOutput output = new RandomOutput(controller.randomRecipe());
        final RandomPresenter presenter = new RandomPresenter();
        final RandomViewModel data = presenter.setRandomViewModel(output);

        recipeTitle.setText(data.getTitle());
        recipeIngredients.setText(data.getIngredients());

        recipeSteps.setText(data.getInstructions());

        final URL url = new URL(data.getImage());
        final Image image = ImageIO.read(url);
        imageLabel.setIcon(new ImageIcon(image));
    }
}
