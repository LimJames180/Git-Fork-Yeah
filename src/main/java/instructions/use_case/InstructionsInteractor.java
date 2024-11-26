package instructions.use_case;

import instructions.data_access.InstructionsDataAccessInterface;

public class InstructionsInteractor implements InstructionsInputBoundary {
    private final InstructionsDataAccessInterface instructionsDataAccess;
    private final InstructionsOutputBoundary instructionsOutputBoundary;

    public InstructionsInteractor(InstructionsDataAccessInterface instructionsDataAccess, InstructionsOutputBoundary instructionsOutputBoundary){
        this.instructionsDataAccess = instructionsDataAccess;
        this.instructionsOutputBoundary = instructionsOutputBoundary;
    }

    @Override
    public void execute(InstructionsInputData instructionsInputData) {
        final int id = instructionsInputData.getId();

        String output = instructionsDataAccess.getInstructions(id);
        InstructionsOutputData instructionsOutputData = new InstructionsOutputData(output);
        instructionsOutputBoundary.setInstructionsViewModel(instructionsOutputData);
        }
    }

