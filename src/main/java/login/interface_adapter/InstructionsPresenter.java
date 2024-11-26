package login.interface_adapter;

import login.use_case.InstructionsInputBoundary;
import login.use_case.InstructionsOutputBoundary;
import login.use_case.InstructionsOutputData;
import login.view.InstructionsView;

public class InstructionsPresenter implements InstructionsOutputBoundary {
    private InstructionsViewModel instructionsViewModel;

    public InstructionsPresenter(InstructionsViewModel instructionsViewModel) {this.instructionsViewModel = instructionsViewModel;}

    public void setInstructionsViewModel(InstructionsOutputData instructionsOutputData) {
        instructionsViewModel.setInstructions(instructionsOutputData.getMessage());
    }
}
