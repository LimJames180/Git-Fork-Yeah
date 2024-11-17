import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultipleToggleButtonExample extends JFrame{
    private boolean[] variables = {true, true, true}; // Array to hold the state of each button

    public MultipleToggleButtonsExample() {
        setTitle("Multiple Toggle Buttons Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        for (int i = 0; i < variables.length; i++) {
            int index = i; // Final or effectively final variable for use in the inner class
            JButton toggleButton = new JButton("Toggle Variable " + (i + 1));
            toggleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    variables[index] = !variables[index];
                    System.out.println("Variable " + (index + 1) + " is now: " + variables[index]);
                }
            });
            add(toggleButton);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultipleToggleButtonsExample example = new MultipleToggleButtonsExample();
            example.setVisible(true);
        });
    }
}