package view;


import javax.swing.*;
import java.awt.*;
import interface_adapter.filter.FilterController;
import java.util.List;


public class FilterView extends JFrame{
    private JButton filterbutton;
    private ToggleButtonsView toggleButtonsExample;
    private FilterController controller;
    private List<String> ingredients = List.of("tomato", "pepper");
    private JPanel inputPanel = new JPanel();


    public FilterView(FilterController controller, ToggleButtonsView toggleButtonsExample) {
        this.controller = controller;
        //this.ingredients = ingredients;
        this.toggleButtonsExample = toggleButtonsExample;
        setTitle("Filters");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Input panel
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);


        add(inputPanel, BorderLayout.NORTH);


        // Button action
        filterbutton.addActionListener(e -> {
            StringBuilder results = controller.handlefilter(ingredients, toggleButtonsExample.getVariables());
            displayResults(results.toString());
        });
    }


    public void displayResults(String results) {
        String[] recipe = results.toString().split("Recipe: ");
        inputPanel.removeAll();
        for (int i = 1; i < recipe.length; i++) {
            String r = recipe[i];
            JButton recipeButton = new JButton(r);
            recipeButton.setText(r);
            recipeButton.addActionListener(e -> System.out.println("Recipe: " + r));
            inputPanel.add(recipeButton);
        }
    }
}
