package view;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import entity.Recipe;
import instructions.view.InstructionsView;
import interface_adapter.filter.FilterController;
import java.util.List;


public class FilterView extends JFrame{
    private JButton filterbutton;
    private JButton backButton;
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
            List<Recipe> results = controller.handlefilter(ingredients, toggleButtonsExample.getVariables(), toggleButtonsExample.getVariables2());
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
    }
}
