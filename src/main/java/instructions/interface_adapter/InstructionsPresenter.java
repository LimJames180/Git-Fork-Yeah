package instructions.interface_adapter;

import instructions.use_case.InstructionsOutputBoundary;
import instructions.use_case.InstructionsOutputData;

public class InstructionsPresenter implements InstructionsOutputBoundary {
    private InstructionsViewModel instructionsViewModel;

    public InstructionsPresenter(InstructionsViewModel instructionsViewModel) {this.instructionsViewModel = instructionsViewModel;}

    public void setInstructionsViewModel(InstructionsOutputData instructionsOutputData) {
        instructionsViewModel.setInstructions(instructionsOutputData.getInstructions());
        instructionsViewModel.setImage(instructionsOutputData.getImage());
        instructionsViewModel.setIngredients(instructionsOutputData.getIngredients());
    }
}
