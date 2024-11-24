package view;


import data_access.FilterDataAccess;
import interface_adapter.filter.FilterController;
import use_case.filter.FilterInteractor;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class ToggleButtonsView extends JFrame {
    private Map<String, Boolean> variables = new HashMap<>();
    private FilterController controller;


    public ToggleButtonsView(FilterController controller) {
        setTitle("Multiple Toggle Buttons Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        String[] buttonNames = {"Gluten-free", "Dairy-free", "Vegan"};
        for (String name : buttonNames) {
            variables.put(name, false);
            JButton toggleButton = new JButton(name);
            toggleButton.addActionListener(e -> {
                variables.put(name, !variables.get(name));
                toggleButton.setText(name + " (" + (variables.get(name) ? "ON" : "OFF") + ")");
                System.out.println(name + " is now: " + variables.get(name));
            });
            add(toggleButton);
        }


        JButton switchButton = new JButton("Done");
        switchButton.addActionListener(e -> {
            dispose();
            FilterView filterSwing = new FilterView(controller,ToggleButtonsView.this);
            filterSwing.setVisible(true);
        });
        add(switchButton);
    }


    public Map<String, Boolean> getVariables() {
        return variables;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FilterDataAccess dataAccess = new FilterDataAccess();
            FilterInteractor interactor = new FilterInteractor(dataAccess);
            FilterController controller = new FilterController(interactor);
            ToggleButtonsView example = new ToggleButtonsView(controller);
            example.setVisible(true);
        });
    }
}
