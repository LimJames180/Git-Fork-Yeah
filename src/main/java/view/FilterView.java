package view;


import javax.swing.*;
import java.awt.*;
import interface_adapter.filter.FilterController;
import java.util.List;


public class FilterView extends JFrame{
    private JButton filterbutton;
    private JTextArea resultArea;
    private ToggleButtonsView toggleButtonsExample;
    private FilterController controller;
    private List<String> ingredients = List.of("tomato", "pepper");
    private JPanel inputPanel = new JPanel();


    public FilterView(List<String> ingredients,FilterController controller, ToggleButtonsView toggleButtonsExample) {
        this.controller = controller;
        this.ingredients = ingredients;
        this.toggleButtonsExample = toggleButtonsExample;
        setTitle("Filters");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Input panel
        inputPanel.setLayout(new FlowLayout());
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);


        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);


        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


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
            JButton recipeButton = new JButton(recipe[i]);
            recipeButton.setText(recipe[i]);
            recipeButton.addActionListener(e -> resultArea.setText(results));
            inputPanel.add(recipeButton);
        }


    }






}
