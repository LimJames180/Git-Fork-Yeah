import javax.swing.*;
import java.awt.*;

public class FilterSwing extends JFrame{
    private JTextField ingredientField;
    private JButton filterbutton;
    private JTextArea resultArea;
    private MultipleToggleButtonsExample toggleButtonsExample;

    public FilterSwing(MultipleToggleButtonsExample toggleButtonsExample) {
        this.toggleButtonsExample = toggleButtonsExample;
        setTitle("Filters");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        filterbutton = new JButton("Find Recipes");
        inputPanel.add(filterbutton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button action
        filterbutton.addActionListener(new FilterButton(this, toggleButtonsExample));
    }

    public void displayResults(String results) {
        resultArea.setText(results);
    }


}
