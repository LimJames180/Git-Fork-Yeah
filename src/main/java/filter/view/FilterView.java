package filter.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Recipe;
import filter.data_access.FilterDataAccess;
import filter.interface_adapter.FilterController;
import filter.interface_adapter.FilterPresenter;
import filter.interface_adapter.FilterViewModel;
import filter.use_case.FilterInteractor;
import instructions.view.BaseView;
import instructions.view.InstructionsView;
import login.app.SessionService;

/**
 * The FilterView class represents the GUI for filtering recipes.
 * It extends JFrame and implements the BaseView interface.
 */
public class FilterView extends JFrame implements BaseView {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 800;
    private static final int IMAGE_SIZE = 50;
    private static final int OFFSET_INCREMENT = 10;
    private final JButton filterbutton;
    private final JButton backButton;
    private final JPanel inputPanel = new JPanel();
    private JButton prevButton;
    private JButton nextButton;
    private int offset;
    private final FilterController controller;
    private final List<String> ingredients;
    private final ToggleButtonsView toggled;
    private final FilterViewModel filterviewmodel;
    private final SessionService currSession;

    public FilterView(List<String> ingredients, ToggleButtonsView toggleButtonsExample, SessionService currentSession) {
        this.ingredients = ingredients;
        this.toggled = toggleButtonsExample;
        this.currSession = currentSession;

        final FilterDataAccess dataAccess = new FilterDataAccess();
        this.filterviewmodel = new FilterViewModel();
        final FilterPresenter filterPresenter = new FilterPresenter(filterviewmodel);
        final FilterInteractor interactor = new FilterInteractor(dataAccess, filterPresenter);
        this.controller = new FilterController(interactor);

        setTitle("Filters");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);
        add(inputPanel, BorderLayout.NORTH);
        // Back button
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        buttonPanel.add(filterbutton);
        inputPanel.add(buttonPanel);

        backButton.addActionListener(event -> {
            dispose();
            toggleButtonsExample.setVisible(true);
        });

        // Button action
        filterbutton.addActionListener(event -> {
            try {
                this.controller.handlefilter(ingredients, this.toggled.getVariables(), this.toggled.getVariables2(), 0);
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            final List<Recipe> results = filterviewmodel.getRecipeList();
            displayResults(results);
        });
    }

    /**
     * Displays the results of the filtered recipes.
     *
     * @param results the list of filtered recipes to display
     * @throws IllegalStateException if no results are found
     */
    public void displayResults(List<Recipe> results) {
        if (results.isEmpty()) {
            System.out.println("No results found");
            throw new IllegalStateException("Condition not met");
        }
        for (Recipe r : results) {
            final String rName = r.getTitle();
            final JButton recipeButton = new JButton(rName);
            if (r.getImage() != null && !r.getImage().isEmpty()) {
                try {
                    final URL imageUrl = new URL(r.getImage());
                    final ImageIcon icon = new ImageIcon(
                            new ImageIcon(imageUrl)
                                    .getImage()
                                    .getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH)
                    );
                    recipeButton.setIcon(icon);
                }
                catch (IOException event) {
                    event.printStackTrace();
                }
            }
            recipeButton.addActionListener(event -> {
                try {
                    final InstructionsView instruction = new InstructionsView(r.getId(), FilterView.this, currSession);
                    instruction.setVisible(true);
                    setVisible(false);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            inputPanel.add(recipeButton);
        }
        inputPanel.revalidate();
        inputPanel.repaint();

        final JPanel nextPrevPanel = new JPanel(new FlowLayout());
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        nextPrevPanel.add(prevButton);
        nextPrevPanel.add(nextButton);
        inputPanel.add(nextPrevPanel);

        prevButton.addActionListener(event -> {
            this.offset -= OFFSET_INCREMENT;
            handleFilter();
        });

        nextButton.addActionListener(event -> {
            this.offset += OFFSET_INCREMENT;
            handleFilter();
        });
    }

    private void handleFilter() {
        try {
            this.controller.handlefilter(ingredients, this.toggled.getVariables(),
                    this.toggled.getVariables2(), this.offset);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        final List<Recipe> result = filterviewmodel.getRecipeList();
        deletePrevRecipes();
        displayResults(result);
    }

    private void deletePrevRecipes() {
        final int size = 11;
        for (int i = size; i >= 1; i--) {
            inputPanel.remove(i);
        }
    }
}
