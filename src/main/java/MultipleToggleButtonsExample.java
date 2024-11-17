import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MultipleToggleButtonsExample extends JFrame {
    private Map<String, Boolean> variables = new HashMap<>();

    public MultipleToggleButtonsExample() {
        setTitle("Multiple Toggle Buttons Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        String[] buttonNames = {"Gluten-free", "Dairy-free", "Vegan"};
        for (String name : buttonNames) {
            variables.put(name, false);
            JButton toggleButton = new JButton(name);
            toggleButton.setBackground(Color.RED); // Initial color
            toggleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    variables.put(name, !variables.get(name));
                    toggleButton.setBackground(variables.get(name) ? Color.GREEN : Color.RED);
                    toggleButton.setText(name + " (" + (variables.get(name) ? "ON" : "OFF") + ")");
                    System.out.println(name + " is now: " + variables.get(name));
                }
            });
            add(toggleButton);
        }

        JButton switchButton = new JButton("Done");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FilterSwing filterSwing = new FilterSwing(MultipleToggleButtonsExample.this);
                filterSwing.setVisible(true);
            }
        });
        add(switchButton);
    }

    public Map<String, Boolean> getVariables() {
        return variables;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultipleToggleButtonsExample example = new MultipleToggleButtonsExample();
            example.setVisible(true);
        });
    }
}