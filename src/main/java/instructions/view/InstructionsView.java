package instructions.view;

import instructions.data_access.InstructionsDataAccess;
import instructions.interface_adapter.InstructionsController;
import instructions.interface_adapter.InstructionsPresenter;
import instructions.interface_adapter.InstructionsViewModel;
import instructions.use_case.InstructionsInteractor;
import view.FilterView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class InstructionsView extends JFrame {
    private InstructionsController instructionsController;
    private JButton backButton;
    private InstructionsViewModel instructionsViewModel;
    private int id;
    private String image;


    public InstructionsView(int id, String image, FilterView filterView) {
        this.id = id;
        this.image = image;
        initializeView();
        setupUI(filterView);
//        setupListeners();
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

        // Top panel for recipe image
        ImageIcon recipeIcon = new ImageIcon(""); //URL of image from API
        JLabel imageLabel = new JLabel(recipeIcon);
//        imageLabel.setIcon(recipeIcon);
        JPanel imagePanel = new JPanel();

        if (image != null && !image.isEmpty()) {
            try {
                URL imageUrl = new URL(image);
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
        }

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
            filterView.setVisible(true);
            // You can show the previous window here
            // Example: new PreviousPage();  // Assuming you have a PreviousPage class
            
        });


        // Add everything to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(imagePanel);
//        mainPanel.add(ingredientsPanel);
        mainPanel.add(instructionsPanel);
        mainPanel.add(imagePanel);
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
