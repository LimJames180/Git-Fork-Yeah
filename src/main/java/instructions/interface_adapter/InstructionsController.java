package instructions.interface_adapter;

import instructions.use_case.InstructionsInputBoundary;
import instructions.use_case.InstructionsInputData;

import java.io.IOException;

public class InstructionsController {
    private final InstructionsInputBoundary instructionsInputBoundary;

    public InstructionsController(InstructionsInputBoundary instructionsInputBoundary) {this.instructionsInputBoundary = instructionsInputBoundary;}

    public void handleInstructions(int id) {
        InstructionsInputData instructionsInputData = new InstructionsInputData(id);
        instructionsInputBoundary.execute(instructionsInputData);
    }
}
