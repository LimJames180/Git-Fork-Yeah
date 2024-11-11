import javax.swing.*;
import java.awt.*;

public class RecipeFinderSwing extends JFrame {
    private JTextField ingredientField;
    private JButton searchButton;
    private JTextArea resultArea;

    public RecipeFinderSwing() {
        setTitle("Recipe Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        ingredientField = new JTextField(20);
        searchButton = new JButton("Find Recipes");
        inputPanel.add(new JLabel("Ingredient:"));
        inputPanel.add(ingredientField);
        inputPanel.add(searchButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button action
        searchButton.addActionListener(new SearchButtonListener(this));
    }

    public String getIngredient() {
        return ingredientField.getText().trim();
    }

    public void displayResults(String results) {
        resultArea.setText(results);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}