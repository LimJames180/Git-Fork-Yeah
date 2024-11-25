package view;


import javax.swing.*;
import java.awt.*;

import entity.Recipe;
import interface_adapter.filter.FilterController;
import java.util.List;


public class FilterView extends JFrame{
    private JButton filterbutton;
    private JPanel inputPanel = new JPanel();


    public FilterView(List<String> ingredients, FilterController controller, ToggleButtonsView toggleButtonsExample) {
        setTitle("Filters");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Input panel
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);
        add(inputPanel, BorderLayout.NORTH);


        // Button action
        filterbutton.addActionListener(e -> {
            List<Recipe> results = controller.handlefilter(ingredients, toggleButtonsExample.getVariables());
            displayResults(results);
        });
    }


    public void displayResults(List<Recipe> results) {
        inputPanel.removeAll();
        for (Recipe r : results) {
            String rName = r.getTitle();
            JButton recipeButton = new JButton(rName);
            recipeButton.addActionListener(e -> System.out.println("Recipe: " + rName));
            inputPanel.add(recipeButton);
        }
        inputPanel.revalidate();
        inputPanel.repaint();
    }
}
