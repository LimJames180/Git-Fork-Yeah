package login.view;

import login.data_access.InstructionsDataAccess;
import login.interface_adapter.InstructionsController;
import login.interface_adapter.InstructionsPresenter;
import login.interface_adapter.InstructionsViewModel;
import login.use_case.InstructionsInteractor;

import javax.swing.*;
import java.awt.*;

public class InstructionsView extends JFrame {
    private JButton instructions;
    private InstructionsController instructionsController;
    private DefaultListModel<String> ingredientsListModel;
    private JList<String> ingredientsList;
    private DefaultListModel<String> instructionsListModel;
    private JList<String> instructionsList;
    private JButton backButton;
    private InstructionsViewModel instructionsViewModel;
    private int id;


    public InstructionsView(int id) {
        this.id = id;
        initializeView();
        setupUI();
//        setupListeners();
    }

    private void initializeView() {
        this.instructionsViewModel = new InstructionsViewModel();
        InstructionsDataAccess instructionsDataAccess = new InstructionsDataAccess();
        InstructionsPresenter instructionsPresenter = new InstructionsPresenter(instructionsViewModel);
        InstructionsInteractor instructionsInteractor = new InstructionsInteractor(instructionsDataAccess,instructionsPresenter);
        this.instructionsController = new InstructionsController(instructionsInteractor);
    }

    private void setupUI() {
        instructionsController.handleInstructions(id);
        setTitle("Recipe Instructions"); // set as name of recipe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new FlowLayout());

        // Top panel for recipe image
        ImageIcon recipeIcon = new ImageIcon(""); //URL of image from API
        JLabel imageLabel = new JLabel(recipeIcon);
        imageLabel.setIcon(recipeIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);

        // Middle panel for ingredients list from API
//        JPanel ingredientsPanel = new JPanel();
//        ingredientsListModel = new DefaultListModel<>();
//        ingredientsList = new JList<>(ingredientsListModel);
//        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsList);
//        JTextArea ingredientsTextArea = new JTextArea(ingredientsViewModel.getIngredients()); // URL of ingredients
//        ingredientsPanel.add(new JLabel("Needed ingredients:"), BorderLayout.NORTH);
//        ingredientsPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
//        ingredientsPanel.add(ingredientsTextArea, BorderLayout.SOUTH);

        // Another panel showing nutritional information??


        // Bottom panel for instructions from API
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BorderLayout());
        JTextArea instructionsTextArea = new JTextArea(instructionsViewModel.getInstructions()); // URL of instructions
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setWrapStyleWord(true);
        instructionsTextArea.setSize(600, 200);
        instructionsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JLabel instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        instructionsPanel.add(instructionsLabel, BorderLayout.NORTH);
        instructionsPanel.add(instructionsTextArea, BorderLayout.CENTER);

        // Panel for back button
        backButton = new JButton("Back");
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);
        instructionsPanel.add(backButtonPanel, BorderLayout.SOUTH);

        // Back button logic
        backButton.addActionListener(e -> {
            dispose(); // Disposes the current JFrame

            // You can show the previous window here
            // Example: new PreviousPage();  // Assuming you have a PreviousPage class
        });


        // Add everything to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(imagePanel);
//        mainPanel.add(ingredientsPanel);
        mainPanel.add(instructionsPanel);
        add(mainPanel);

//        frame.add(imagePanel);
////      frame.add(ingredientsPanel);
//        frame.add(instructionsPanel);
        setVisible(true);
    }
//    private void setupListeners() {
//        backButton.addActionListener(new backButtonListener());
//
//        setVisible(true);
//    }
}
