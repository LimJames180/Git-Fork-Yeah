package instructions.view;

import instructions.data_access.InstructionsDataAccess;
import instructions.interface_adapter.InstructionsController;
import instructions.interface_adapter.InstructionsPresenter;
import instructions.interface_adapter.InstructionsViewModel;
import instructions.use_case.InstructionsInteractor;
import view.FilterView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class InstructionsView extends JFrame {
    private InstructionsController instructionsController;
    private JButton backButton;
    private JButton saveButton;
    private InstructionsViewModel instructionsViewModel;
    private int id;


    public InstructionsView(int id, FilterView filterView) throws IOException {
        this.id = id;
        initializeView();
        setupUI(filterView);
    }

    private void initializeView() {
        this.instructionsViewModel = new InstructionsViewModel();
        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();
        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
        InstructionsInteractor instructionsInteractor = new InstructionsInteractor(instructionsDataAccess,instructionsPresenter);
        this.instructionsController = new InstructionsController(instructionsInteractor);
    }

    private void setupUI(FilterView filterView) {
        instructionsController.handleInstructions(id);
        setTitle("Recipe Instructions"); // set as name of recipe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(new FlowLayout());


        // Middle panel for ingredients list from API
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BorderLayout());
        JTextArea ingredientsTextArea = new JTextArea(instructionsViewModel.getIngredients());
        ingredientsTextArea.setLineWrap(true);
        ingredientsTextArea.setWrapStyleWord(true);
        ingredientsTextArea.setSize(600, 200);
        ingredientsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ingredientsPanel.add(ingredientsLabel, BorderLayout.NORTH);
        ingredientsPanel.add(ingredientsTextArea, BorderLayout.CENTER);
        // Another panel showing nutritional information??


        // Bottom panel for instructions from API
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BorderLayout());
        JTextArea instructionsTextArea = new JTextArea(instructionsViewModel.getInstructions());
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true);
        instructionsTextArea.setSize(600, 200);
        instructionsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Top panel for recipe image
        JLabel imageLabel = new JLabel();
        JPanel imagePanel = new JPanel();

        try {
            URL imageUrl = new URL(instructionsViewModel.getImage());
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(imageUrl)
                            .getImage()
                            .getScaledInstance(150, 150, Image.SCALE_SMOOTH)
            );
            imageLabel.setIcon(icon);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Error loading image for recipe");
            e.printStackTrace();
        }
        System.out.println(instructionsViewModel.getImage());
        // Panel for back button
        backButton = new JButton("Back");
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        instructionsPanel.add(instructionsLabel, BorderLayout.NORTH);
        instructionsPanel.add(instructionsTextArea, BorderLayout.CENTER);
        instructionsPanel.add(imagePanel, BorderLayout.EAST);

        // Back button logic
        backButton.addActionListener(e -> {
            dispose(); // Disposes the current JFrame
            filterView.setVisible(true);
        });

        // Panel for save button
        saveButton = new JButton("Save Recipe!");
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.add(saveButton);

        // Save button logic
        saveButton.addActionListener(e -> {

        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(saveButton, BorderLayout.EAST);

        // Add everything to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(ingredientsPanel, BorderLayout.NORTH);
        mainPanel.add(instructionsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setVisible(true);
    }
}
