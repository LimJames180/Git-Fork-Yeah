package login.interface_adapter;

import login.use_case.InstructionsInputBoundary;
import login.use_case.InstructionsInputData;

public class InstructionsController {
    private final InstructionsInputBoundary instructionsInputBoundary;

    public InstructionsController(InstructionsInputBoundary instructionsInputBoundary) {this.instructionsInputBoundary = instructionsInputBoundary;}

    public void handleInstructions(int id) {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInputBoundary.execute(instructionsInputData);
    }
}
