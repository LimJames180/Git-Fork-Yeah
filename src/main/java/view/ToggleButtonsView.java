package view;


import data_access.FilterDataAccess;
import interface_adapter.filter.FilterController;
import login.app.SessionService;
import use_case.filter.FilterInteractor;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ToggleButtonsView extends JFrame {
    private Map<String, Boolean> variables = new HashMap<>();
    private Map<String, Boolean> variables2 = new HashMap<>();
    private JButton backButton;


    public ToggleButtonsView(List<String> ingredients, FilterController controller, SessionService currentSession) {
        setTitle("Multiple Toggle Buttons Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        String[] buttonNames = {"glutenfree", "vegetarian", "vegan", "ketogenic"};
        for (String name : buttonNames) {
            variables.put(name, false);
            JButton toggleButton = new JButton(name.substring(0, 1).toUpperCase() + name.substring(1));
            toggleButton.addActionListener(e -> {
                variables.put(name, !variables.get(name));
                toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1) + " (" + (variables.get(name) ? "ON" : "OFF") + ")");
                System.out.println(name + " is now: " + variables.get(name));
            });
            add(toggleButton);
        }

        String[] buttonNames2 = {"dairy", "egg", "peanut", "seafood"};
        for (String name : buttonNames2) {
            variables2.put(name, false);
            JButton toggleButton = new JButton(name.substring(0, 1).toUpperCase() + name.substring(1)+ " Free");
            toggleButton.addActionListener(e -> {
                variables2.put(name, !variables2.get(name));
                toggleButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1)+ " Free" + " (" + (variables2.get(name) ? "ON" : "OFF") + ")");
                System.out.println(name + " is now: " + variables2.get(name));
            });
            add(toggleButton);
        }


        JButton switchButton = new JButton("Done");
        switchButton.addActionListener(e -> {
            dispose();
            FilterView filterSwing = new FilterView(ingredients, controller,ToggleButtonsView.this, currentSession);
            filterSwing.setVisible(true);
        });
        add(switchButton);

//        // Back button
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        backButton = new JButton("Back");
//        buttonPanel.add(backButton);
//        add(buttonPanel);
//
//        backButton.addActionListener(e -> {
//            dispose();
//            .setVisible(true);
//        });

    }


    public Map<String, Boolean> getVariables() {
        return variables;
    }

    public Map<String, Boolean> getVariables2() {
        return variables2;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<String> ingredients = List.of("chicken");
            FilterDataAccess dataAccess = new FilterDataAccess();
            FilterInteractor interactor = new FilterInteractor(dataAccess);
            FilterController controller = new FilterController(interactor);
            SessionService test = new SessionService();
            test.setUsername("callon");
            ToggleButtonsView example = new ToggleButtonsView(ingredients, controller, test);
            example.setVisible(true);
        });
    }
}
