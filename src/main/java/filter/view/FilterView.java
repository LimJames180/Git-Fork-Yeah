package filter.view;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import entity.Recipe;
import filter.data_access.FilterDataAccess;
import filter.interface_adapter.FilterPresenter;
import filter.interface_adapter.FilterViewModel;
import filter.use_case.FilterInteractor;
import instructions.view.InstructionsView;
import filter.interface_adapter.FilterController;

import java.util.List;


public class FilterView extends JFrame{
    private JButton filterbutton;
    private JButton backButton;
    private JPanel inputPanel = new JPanel();
    private JButton prevButton;
    private JButton nextButton;
    private int offset;
    private FilterController controller;
    private List<String> ingredients;
    private ToggleButtonsView toggleButtonsExample;
    private FilterViewModel filterviewmodel;


    public FilterView(List<String> ingredients, ToggleButtonsView toggleButtonsExample) {
        this.ingredients = ingredients;
        this.toggleButtonsExample = toggleButtonsExample;

        FilterDataAccess dataAccess = new FilterDataAccess();
        this.filterviewmodel = new FilterViewModel();
        FilterPresenter filterPresenter = new FilterPresenter(filterviewmodel);
        FilterInteractor interactor = new FilterInteractor(dataAccess, filterPresenter);
        FilterController controller = new FilterController(interactor);
        this.controller = controller;
       // this.filterviewmodel = new FilterViewModel();
        //FilterDataAccess filterDataAccess = new FilterDataAccess();
        //FilterPresenter filterPresenter = new FilterPresenter(filterviewmodel);
        //FilterInteractor filterInteractor = new FilterInteractor(filterPresenter);

        setTitle("Filters");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Input panel
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);
        add(inputPanel, BorderLayout.NORTH);

        // Back button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        buttonPanel.add(filterbutton);
        inputPanel.add(buttonPanel);

        backButton.addActionListener(e -> {
            dispose();
            toggleButtonsExample.setVisible(true);
        });


        // Button action
        filterbutton.addActionListener(e -> {
            this.controller.handlefilter(ingredients, this.toggleButtonsExample.getVariables(), this.toggleButtonsExample.getVariables2(),0);
            List<Recipe> results = filterviewmodel.getRecipeList();

            displayResults(results);
        });



    }


    public void displayResults(List<Recipe> results) {
        if (results.isEmpty()){
            System.out.println("No results found");
            return;
        }
        for (Recipe r : results) {
            String rName = r.getTitle();
            JButton recipeButton = new JButton(rName);
            if (r.getImage() != null && !r.getImage().isEmpty()) {
                try {
                    URL imageUrl = new URL(r.getImage());
                    ImageIcon icon = new ImageIcon(
                            new ImageIcon(imageUrl)
                                    .getImage()
                                    .getScaledInstance(50, 50, Image.SCALE_SMOOTH)
                    );
                    recipeButton.setIcon(icon);
                } catch (Exception e) {
                    System.out.println("Error loading image for recipe: " + rName);
                    e.printStackTrace();
                }
            }
            recipeButton.addActionListener(e -> {
                InstructionsView instructionsView = null;
                try {
                    instructionsView = new InstructionsView(r.getId(), FilterView.this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                instructionsView.setVisible(true);
                setVisible(false);

            });

            inputPanel.add(recipeButton);
        }
        inputPanel.revalidate();
        inputPanel.repaint();


        JPanel nextPrevPanel = new JPanel(new FlowLayout());
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        nextPrevPanel.add(prevButton);
        nextPrevPanel.add(nextButton);
        inputPanel.add(nextPrevPanel);

        prevButton.addActionListener(e -> {
            offset -= 10;
            for (int i = 11; i >= 1; i--) {
                inputPanel.remove(i);
            }
//            for (int i = 1; i >= 10; i++) {
//                inputPanel.remove(i);
//            }
            this.controller.handlefilter(ingredients, this.toggleButtonsExample.getVariables(), this.toggleButtonsExample.getVariables2(), offset);
            List<Recipe> result = filterviewmodel.getRecipeList();

            displayResults(result);
        });

        nextButton.addActionListener(e -> {
            offset += 10;
            this.controller.handlefilter(ingredients, this.toggleButtonsExample.getVariables(), this.toggleButtonsExample.getVariables2(), offset);
            List<Recipe> result = filterviewmodel.getRecipeList();
            //inputPanel.remove();
            for (int i = 11; i >= 1; i--) {
                inputPanel.remove(i);
            }

            displayResults(result);
        });
    }
}
